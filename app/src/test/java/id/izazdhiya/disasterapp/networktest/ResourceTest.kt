package id.izazdhiya.disasterapp.networktest

import id.izazdhiya.disasterapp.data.source.network.Resource
import id.izazdhiya.disasterapp.data.source.network.Status
import org.junit.Test
import org.junit.Assert.assertEquals

class ResourceTest {

    @Test
    fun `test Resource success`() {
        val data = "Success Data"
        val resource = Resource.success(data)

        assertEquals(Status.SUCCESS, resource.status)
        assertEquals(data, resource.data)
        assertEquals(null, resource.message)
    }

    @Test
    fun `test Resource error`() {
        val data = null
        val message = "Error Message"
        val resource = Resource.error(data, message)

        assertEquals(Status.ERROR, resource.status)
        assertEquals(data, resource.data)
        assertEquals(message, resource.message)
    }

    @Test
    fun `test Resource loading`() {
        val data = "Loading Data"
        val resource = Resource.loading(data)

        assertEquals(Status.LOADING, resource.status)
        assertEquals(data, resource.data)
        assertEquals(null, resource.message)
    }
}
