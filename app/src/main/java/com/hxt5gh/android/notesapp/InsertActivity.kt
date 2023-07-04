package com.hxt5gh.android.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.hxt5gh.android.notesapp.Reposetory.NotesRepository
import com.hxt5gh.android.notesapp.ViewModel.NotesViewModel
import com.hxt5gh.android.notesapp.ViewModel.NotesViewModelFactory
import com.hxt5gh.android.notesapp.database.Notes
import com.hxt5gh.android.notesapp.database.NotesDatabase
import com.hxt5gh.android.notesapp.database.newNotes
import com.hxt5gh.android.notesapp.databinding.ActivityInsertBinding
import java.text.SimpleDateFormat
import java.util.*

class InsertActivity : AppCompatActivity() {

    lateinit var binding: ActivityInsertBinding
   lateinit var  title : String
   lateinit var note : String
    var priority :String =  "1"
   lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_insert)
        setSupportActionBar(binding.inserttoolbar)
        binding.inserttoolbar.setTitle("Insert")
        binding.inserttoolbar.setNavigationOnClickListener(){
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            finish()
        }




        val repository = NotesRepository(NotesDatabase.getDataBase(applicationContext))

        viewModel = ViewModelProvider(this , NotesViewModelFactory(repository , this))[NotesViewModel::class.java]

        binding.floatingActionButton3.setOnClickListener(){

            title = binding.idTitle.text.toString()
            note = binding.idNote.text.toString()

            if (TextUtils.isEmpty(title))
            {
                Toast.makeText(this, "Title is Empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (TextUtils.isEmpty(note))
            {
                Toast.makeText(this, "Write Your Note...", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            dataInsert(title , note , priority)

        }

        binding.priorityGreen.setOnClickListener(){
            priority = "1"
            binding.priorityGreen.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_done_24))
            binding.priorityYellow.setImageDrawable(null)
            binding.priorityRed.setImageDrawable(null)
        }
        binding.priorityYellow.setOnClickListener()
        {
            priority = "2"
            binding.priorityGreen.setImageDrawable(null)
            binding.priorityYellow.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_done_24))
            binding.priorityRed.setImageDrawable(null)
        }
        binding.priorityRed.setOnClickListener()
        {
            priority = "3"
            binding.priorityGreen.setImageDrawable(null)
            binding.priorityYellow.setImageDrawable(null)
            binding.priorityRed.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_done_24))
        }


    }

    private fun dataInsert(title: String, note: String , priority : String) {


        val dateFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
        val currentDate = Date()
        val formattedDate = dateFormat.format(currentDate)


        val notes = newNotes(0 , title , note , formattedDate, priority)

        viewModel.insertNotes(notes)
        Toast.makeText(this, "Note Save successfully", Toast.LENGTH_SHORT).show()
        val intent = Intent(this , MainActivity::class.java)
        startActivity(intent)

    }


}

