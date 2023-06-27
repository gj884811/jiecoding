package repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.frogapplication.data.repository.AuthViewModel
import com.example.frogapplication.data.repository.MovieViewModel

/**
 * ViewModelFactory to construct the view models required.
 */
class MovieViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}
