package com.example.grouphomework

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.grouphomework.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()
    lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeMovieList()
        binding.ivBackArrow.setOnClickListener{
        //     val intent= Intent(this,MainActivity::class.java)
        //     startActivity(intent)
        }
        binding.ivCleanSearch.setOnClickListener{
            binding.etSearch.text.clear()
        }
        binding.ivCloseKeyboard.setOnClickListener{
            var imm:InputMethodManager =  getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.ivCloseKeyboard.windowToken, 0)
        }

        binding.etSearch.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("beforeTextChanged")
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!s.isNullOrEmpty()){
                    val filteredList = Database.movies.filter {
                        it.name.contains(s.toString(), ignoreCase = true)
                    }
                    adapter.updateList(filteredList)
                }else {
                    adapter.updateList(Database.movies)
                }
                adapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable?) {
                println("afterTextChanged")
            }
        })
    }

    private fun observeMovieList(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.movieListState.collect{
                    when(it){
                        MovieListState.Idle->{}
                        MovieListState.Loading->{
                            binding.progressBar.isVisible =true
                            binding.rvMovies.isVisible=false
                        }
                        is MovieListState.Result->{
                            binding.progressBar.isVisible=false
                            binding.rvMovies.isVisible =true
                            adapter = MovieListAdapter(this@MainActivity,Database.movies)
                            binding.rvMovies.adapter=adapter
                        }
                        is MovieListState.Error->{
                            binding.rvMovies.isVisible=false
                            AlertDialog.Builder(this@MainActivity).setTitle("Error").setMessage("liste gelirken sorun oluştu").create().show()
                        }
                    }

                }
            }
        }
    }
}