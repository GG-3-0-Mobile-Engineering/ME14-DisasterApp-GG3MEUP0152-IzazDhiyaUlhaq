package id.izazdhiya.disasterapp.ui.fragment

import android.content.Intent
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import id.izazdhiya.disasterapp.R
import id.izazdhiya.disasterapp.ui.SettingsActivity
import id.izazdhiya.disasterapp.ui.adapter.DisasterAdapter
import id.izazdhiya.disasterapp.ui.adapter.DisasterTypeAdapter
import id.izazdhiya.disasterapp.databinding.FragmentMapsBinding
import id.izazdhiya.disasterapp.data.source.DisasterType
import id.izazdhiya.disasterapp.data.source.network.Resource
import id.izazdhiya.disasterapp.data.source.network.Status
import id.izazdhiya.disasterapp.data.source.network.response.DisasterReport
import id.izazdhiya.disasterapp.ui.viewmodel.DisasterViewModel

@AndroidEntryPoint
class MapsFragment : Fragment() {

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!

    private lateinit var disasterTypeAdapter: DisasterTypeAdapter
    private lateinit var disasterTypeList: ArrayList<id.izazdhiya.disasterapp.data.source.DisasterType>

    private lateinit var disasterAdapter: DisasterAdapter

    private val disasterViewModel: DisasterViewModel by viewModels()

    private lateinit var disasterReport: Resource<DisasterReport>
    private lateinit var typeSelected: String

    private var area: String? = null
    private var startDate: String? = null
    private var endDate: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        area = arguments?.getString("area")
        startDate = arguments?.getString("startDate")
        endDate = arguments?.getString("endDate")

        val callback = OnMapReadyCallback { googleMap ->
            if (!area.isNullOrBlank()) {
                observeReportsByProvince(area!!, googleMap)
                arguments?.remove("area")
            } else if (!startDate.isNullOrBlank() && !endDate.isNullOrBlank()) {
                observeArchive(startDate!!, endDate!!, googleMap)
                arguments?.remove("startDate")
                arguments?.remove("endDate")
            } else {
                observeReports(googleMap)
            }
            createDisasterType(googleMap)
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        moveToSettings()
        moveToSearch()
    }

    private fun createDisasterType(map: GoogleMap) {

        disasterTypeList = ArrayList<id.izazdhiya.disasterapp.data.source.DisasterType>().apply {
            addAll(
                listOf(
                    DisasterType("all", "All"),
                    DisasterType("flood", "Flood"),
                    DisasterType("earthquake", "Earthquake"),
                    DisasterType("fire", "Fire"),
                    DisasterType("haze", "Haze"),
                    DisasterType("wind", "Wind"),
                    DisasterType("volcano", "Volcano")
                )
            )
        }

        disasterTypeAdapter = DisasterTypeAdapter(disasterTypeList) { id: String, _ ->
            binding.map.isVisible = false
            binding.lottieNodata.isVisible = false
            binding.lottieSearchlocation.isVisible = true
            map.clear()
            typeSelected = id
            setData(disasterReport, map, id)
        }

        binding.rvDisasterType.apply {
            adapter = disasterTypeAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun statusObserve(it: Resource<DisasterReport>, map: GoogleMap){
        when (it.status) {
            Status.SUCCESS -> {
                binding.pbDisaster.isVisible = false
                setData(it, map, "all")
            }
            Status.ERROR -> {
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                binding.pbDisaster.isVisible = false
                binding.lottieNodata.isVisible = true
                binding.lottieSearchlocation.isVisible = false
                binding.clSheet.isVisible = false
            }
            Status.LOADING -> {
                binding.pbDisaster.isVisible = true
                binding.clSheet.isVisible = false
            }
        }
    }

    private fun setData(it: Resource<DisasterReport>, map: GoogleMap, typeDisaster: String){
        disasterReport = it
        binding.lottieSearchlocation.isVisible = false
        if (it.data?.statusCode == 200) {
            var disaster = it.data.result?.features
            if (typeDisaster != "all") {
                disaster = disaster?.filter { element ->
                    element.properties.disasterType == typeDisaster
                }
            }
            if (!disaster.isNullOrEmpty()) {
                binding.rvDisasterType.isVisible = true
                binding.clSheet.isVisible = true
                binding.map.isVisible = true
                binding.tvNodata.isVisible = false

                for (element in disaster) {
                    val latLng = LatLng(element.geometry.coordinates[1], element.geometry.coordinates[0])
                    map.addMarker(MarkerOptions().position(latLng).title(element.properties.disasterType))
                }

                val firstElement = disaster[0]
                val latLng = LatLng(firstElement.geometry.coordinates[1], firstElement.geometry.coordinates[0])
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7.0f))

                disasterAdapter = DisasterAdapter(disaster)
                binding.rvItemBencana.apply {
                    adapter = disasterAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                }

                initBottomSheet()

            } else {
                Toast.makeText(requireContext(), R.string.tidak_ada_data, Toast.LENGTH_SHORT).show()
                binding.lottieNodata.isVisible = true
                binding.tvNodata.isVisible = true
                binding.clSheet.isVisible = false
            }
        } else {
            Toast.makeText(requireContext(), R.string.bad_request, Toast.LENGTH_SHORT).show()
            binding.lottieNodata.isVisible = true
            binding.tvNodata.isVisible = true
            binding.clSheet.isVisible = false
        }
    }

    private fun observeReports(map: GoogleMap) {
        disasterViewModel.getReports().observe(viewLifecycleOwner) {
            statusObserve(it, map)
        }
    }

    private fun observeReportsByProvince(provinceId: String, map: GoogleMap) {
        disasterViewModel.getReportsByProvince(provinceId).observe(viewLifecycleOwner) {
            statusObserve(it, map)
        }
    }

    private fun observeArchive(startDate: String, endDate: String, map: GoogleMap) {
        disasterViewModel.getArchive(startDate, endDate).observe(viewLifecycleOwner) {
            statusObserve(it, map)
        }
    }

    private fun moveToSettings() {
        binding.buttonSetting.setOnClickListener {
            val intent = Intent(requireActivity(), SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun moveToSearch() {
        binding.searchBar.setOnClickListener {
            it.findNavController().navigate(R.id.action_mapsFragment_to_searchFragment)
        }
    }

    private fun initBottomSheet() {
        val sheet = requireView().findViewById<FrameLayout>(R.id.sheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(sheet)

        val halfScreenHeight = resources.displayMetrics.heightPixels / 2
        bottomSheetBehavior.peekHeight = halfScreenHeight
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.isFitToContents = true
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    bottomSheetBehavior.peekHeight = 100
                    bottomSheetBehavior.isHideable = true
                    binding.searchBar.isVisible = false
                    binding.rvDisasterType.isVisible = false

                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.isFitToContents = true
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    bottomSheetBehavior.peekHeight = 100
                    bottomSheetBehavior.isHideable = false
                    binding.searchBar.isVisible = true
                    binding.rvDisasterType.isVisible = true
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // Handle the sliding behavior (optional)
            }
        })
    }
}