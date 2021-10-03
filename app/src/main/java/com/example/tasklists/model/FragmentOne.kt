package com.example.tasklists.model

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklists.R
import com.example.tasklists.adapter.ItemAdapter

class FragmentOne : Fragment() {
    val args: FragmentOneArgs by navArgs() //Safe args allows you to enter info into the nav graph about the arguments you want to pass. SafeArgs is a plug.
    private val viewModel: ListViewModel by activityViewModels() //Create a val that associates your ViewModel class (e.g., ListViewModel) with your UI controller (fragment, e.g., FragmentOne)
    //Syntax for kotlin property delegate 'viewModels()': var <property-name> : <property-type> by <delegate-class>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val receiveTask =
            view.findViewById<EditText>(R.id.enter_task) //EditText for the FragmentOne is defined.
        val updates =
            args.updateNewData //Attach updateNewData, an argument created in nav graph, to args and assign to val updates.
        receiveTask.setText(updates)//Set info inputted by user in the EditText and pass in the argument of updateNewData.

        view.findViewById<Button>(R.id.changeTheTask)
            .setOnClickListener {//Created onClickListener for EditTask button in FragmentOne

                val editData =
                    view.findViewById<EditText>(R.id.enter_task).text.toString() //Define user's inputted data in EditText line.
                val sendToTwo =
                    FragmentOneDirections.actionFragmentOneToFragmentTwo(editData)//Create a val sendToTwo that passes user's data/editData from Fragment One to Two.
                findNavController().navigate(sendToTwo)// Use the findNavController to give destination direction

            }
        val recyclerView =
            view.findViewById<RecyclerView>(R.id.recycler_view) //Find resource ID for recyclerView in display view.
        val adapter = ItemAdapter(
            viewModel.typeList,
            object :
                ItemAdapter.onClickListner { //In the ItemAdaptor add parameters for the list data saved in the viewModel class and the object ItemAdaptor.
                override fun itemViewClicked(position: Int) {
                    val clickedLineItem =
                        viewModel.typeList[position] //This and following lines allow for each clicked item in the recyclerView list, and saved in the ViewModel, to be elected at a specific position and sent to FragmentTwo.
                    val directionals =
                        FragmentOneDirections.actionFragmentOneToFragmentTwo(clickedLineItem)
                    view.findNavController().navigate(directionals)
                    viewModel.positionClicked = position
                }
            })  //Pass in list to ItemAdapter and assign it to adapter variable.
        recyclerView.adapter = adapter //Bind the adapter to recyclerview to fill listed items.
        recyclerView.layoutManager =
            LinearLayoutManager(context) //The layoutManager positions the items correctly.

        recyclerView.setHasFixedSize(true)
        //Makes for better run performance when you set recyclerView to fixed size knowing content changes won't change layout size

        view.findViewById<Button>(R.id.button2).setOnClickListener {
            val userInput = view.findViewById<EditText>(R.id.enter_task).text.toString() //Implement user inputted TO-Do items.
            Log.i("MainActivity", "data type")
            viewModel.typeList.add(userInput) //Append typelist list to the viewmodel and add in the user's inputted info.
            adapter.notifyDataSetChanged()//Notify adaptor of changes.
        }
        val updateNewData = args.updateNewData
        if (!updateNewData.isNullOrEmpty()) { //Create a conditional statement that handles when the (FragmentOne) updateNewData argument has no value/empty. If so, let the updateNewData argument serves as each typelist item chosen at a specific position. (reference ViewModel class)
            viewModel.typeList[viewModel.positionClicked] = updateNewData
            adapter.notifyDataSetChanged() //Notify adaptor of changes
        }
    }
}



