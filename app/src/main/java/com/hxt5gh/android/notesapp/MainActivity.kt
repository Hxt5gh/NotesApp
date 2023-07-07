package com.hxt5gh.android.notesapp


import android.content.Intent
import android.icu.lang.UCharacter.VerticalOrientation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hxt5gh.android.notesapp.Reposetory.NotesRepository
import com.hxt5gh.android.notesapp.ViewModel.NotesViewModel
import com.hxt5gh.android.notesapp.ViewModel.NotesViewModelFactory
import com.hxt5gh.android.notesapp.database.Notes
import com.hxt5gh.android.notesapp.database.NotesDatabase
import com.hxt5gh.android.notesapp.databinding.ActivityMainBinding
import java.util.*
import  androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    private  val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    lateinit var database: NotesDatabase
    lateinit var viewModel: NotesViewModel
    lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitle("Notes App")

        database = NotesDatabase.getDataBase(applicationContext)
        val repository = NotesRepository(NotesDatabase.getDataBase(applicationContext))

        viewModel = ViewModelProvider(this , NotesViewModelFactory(repository , this))[NotesViewModel::class.java]

        binding.floatingActionButton3.setOnClickListener(){
            val intent = Intent(this , InsertActivity::class.java)
            startActivity(intent)
        }
        viewModel.notes.observe(this  , Observer {
            binding.idRecyclerView.layoutManager = StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL)
            adapter = Adapter(this , it)
            binding.idRecyclerView.adapter = adapter
        })


        binding.nofilter.setBackgroundResource(R.drawable.filter_selected)

        binding.nofilter.setOnClickListener(){
            viewModel.notes.observe(this  , Observer {
                binding.idRecyclerView.layoutManager = StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL)
                adapter = Adapter(this , it)
                binding.idRecyclerView.adapter = adapter
            })
            binding.nofilter.setBackgroundResource(R.drawable.filter_selected)
            binding.lowtoHigh.setBackgroundResource(R.drawable.filter_shape)
            binding.hightoLow.setBackgroundResource(R.drawable.filter_shape)

        }
        binding.lowtoHigh.setOnClickListener(){
            viewModel.notesLowToHigh.observe(this  , Observer {
                binding.idRecyclerView.layoutManager = StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL)
                adapter = Adapter(this , it)
                binding.idRecyclerView.adapter = adapter
            })
            binding.nofilter.setBackgroundResource(R.drawable.filter_shape)
            binding.lowtoHigh.setBackgroundResource(R.drawable.filter_selected)
            binding.hightoLow.setBackgroundResource(R.drawable.filter_shape)
        }
        binding.hightoLow.setOnClickListener() {
            viewModel.notesHighToLow.observe(this  , Observer {
                binding.idRecyclerView.layoutManager = StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL)
                adapter = Adapter(this , it)
                binding.idRecyclerView.adapter = adapter
            })
            binding.nofilter.setBackgroundResource(R.drawable.filter_shape)
            binding.lowtoHigh.setBackgroundResource(R.drawable.filter_shape)
            binding.hightoLow.setBackgroundResource(R.drawable.filter_selected)
        }



    }
}