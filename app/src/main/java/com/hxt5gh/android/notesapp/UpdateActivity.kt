package com.hxt5gh.android.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.hxt5gh.android.notesapp.Reposetory.NotesRepository
import com.hxt5gh.android.notesapp.ViewModel.NotesViewModel
import com.hxt5gh.android.notesapp.ViewModel.NotesViewModelFactory
import com.hxt5gh.android.notesapp.database.NotesDatabase
import com.hxt5gh.android.notesapp.database.newNotes
import com.hxt5gh.android.notesapp.databinding.ActivityUpdateBinding
import java.text.SimpleDateFormat
import java.util.*

class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    lateinit var viewModel: NotesViewModel
    lateinit var title : String
    lateinit var priority : String
    lateinit var note : String
    lateinit var date : String
    private var id : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update)
        setSupportActionBar(binding.Updatetoolbar)

        binding.Updatetoolbar.setNavigationOnClickListener()
        {
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        val repository = NotesRepository(NotesDatabase.getDataBase(applicationContext))
        viewModel = ViewModelProvider(this , NotesViewModelFactory(repository , this ))[NotesViewModel::class.java]

        val intent : Intent = getIntent()
         title = intent.getStringExtra("title").toString()
         priority = intent.getStringExtra("priority").toString()
         note = intent.getStringExtra("note").toString()
         date= intent.getStringExtra("date").toString()
         id = intent.getIntExtra("id" , 0)


        binding.idTitle.setText(title)
        binding.idNote.setText(note)


        if (priority.equals("1")){

            binding.priorityGreen.setImageDrawable(ContextCompat.getDrawable( this , R.drawable.ic_done_24))
        }
       else if (priority.equals("2")){
            binding.priorityYellow.setImageDrawable(ContextCompat.getDrawable(this , R.drawable.ic_done_24))
        }
       else if (priority.equals("3")){
            binding.priorityRed.setImageDrawable(ContextCompat.getDrawable(this , R.drawable.ic_done_24))
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







        binding.floatingActionButton3.setOnClickListener(){

//             val dateFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
//             val currentDate = Date()
//             val formattedDate = dateFormat.format(currentDate)
             val t = binding.idTitle.text.toString()
             val n = binding.idNote.text.toString()


            if (TextUtils.isEmpty(t))
            {
                Toast.makeText(this, "Enter Title ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(n))
            {
                Toast.makeText(this, "Enter Notes", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }



            val notes = newNotes(id , t , n , date , priority)
            viewModel.updateNotes(notes)
            finish()
            Toast.makeText(this, "Note Updated SuccessFully ", Toast.LENGTH_SHORT).show()

        }


    }
    fun delete()
    {
        viewModel.deleteNote(id)
        finish()
        Toast.makeText(this, "Note Delete Successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menue , menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.delete)
        {
           val bottomSheetDialog : BottomSheetDialog =  BottomSheetDialog(this  , R.style.BottomSheetStyle)
            val  view = LayoutInflater.from(this).inflate(R.layout.bottum_sheet , findViewById(R.id.bottumsheet))
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.show()

            val yes  = view.findViewById<TextView>(R.id.deleteYes)
            val no  = view.findViewById<TextView>(R.id.deleteNO)

            yes.setOnClickListener(){

              delete()
            }
            no.setOnClickListener(){

                bottomSheetDialog.dismiss()
            }

            bottomSheetDialog.show()
        }
        return super.onOptionsItemSelected(item)
    }
}