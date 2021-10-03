package com.example.tasklists.model

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tasklists.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FragmentTwo : Fragment() {
    val args: FragmentTwoArgs by navArgs() //Safe args allows you to enter info into the nav graph about the arguments you want to pass. SafeArgs is a plug.

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(
            R.layout.fragment_two,
            container,
            false
        ) //onCreateView should inflate the class FragmentTwo's layout.
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("app", "get data back")
        val getData: EditText =
            view.findViewById(R.id.saver_task) //Created a var to stand for data entered into the EditText box.
        val task = args.editData //Corresponds with safeArgs up top.
        getData.setText(task) //Sets the info the user types in the EditText - saver_task - and passes in the argument editData.

        view.findViewById<Button>(R.id.buttonF2)
            .setOnClickListener { //OnClickListener for the save button on FragmentTwo page.
                val newlyWrittenData =
                    view.findViewById<EditText>(R.id.saver_task).text.toString() //Defines the info user types in EditText box.
                val backToOne =
                    FragmentTwoDirections.actionFragmentTwoToFragmentOne(newlyWrittenData)// The class Directions is auto-created from using safeArgs to pass in data and navigate between fragments/activities.
                findNavController().navigate(backToOne) //Use the NavController to specify destination direction.
            }
    }

}