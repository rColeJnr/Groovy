package com.rick.groovy.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rick.groovy.utils.BaseUnitTest
import com.rick.groovy.utils.getValueForTest
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PlaylistViewModelShould: BaseUnitTest() {

    val repository: PlaylistRepository = mock()
    val playlists = mock<List<Playlist>>()
    val expected = Result.success(playlists)

    init {




    }

    @Test
    fun getPlaylistFromRepository() = runBlockingTest {
        val viewModel = mockSuccessfulState()

        viewModel.playlists.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistsFromRepository() = runBlocking{
        val viewModel = mockSuccessfulState()

        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    private fun mockSuccessfulState(): PlaylistViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }

        val viewModel = PlaylistViewModel(repository)
        return viewModel
    }

}