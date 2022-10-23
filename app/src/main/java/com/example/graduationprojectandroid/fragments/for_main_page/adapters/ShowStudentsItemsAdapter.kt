package com.example.graduationprojectandroid.fragments.for_main_page.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.App
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutShowStudentsItemBinding
import com.example.graduationprojectandroid.fragments.AskQuestionDialogue
import com.example.graduationprojectandroid.network.DataService
import com.example.graduationprojectandroid.data.Items.StudentItem

class ShowStudentsItemsAdapter(
    private var fragmentManager: FragmentManager,
    private var items_arr: ArrayList<StudentItem>,
    private var studentAssignmentsListener: (StudentItem)->Unit,
    private var updateListener: ()->Unit
) : RecyclerView.Adapter<ShowStudentsItemsAdapter.ItemView>()
{

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutShowStudentsItemBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(fragmentManager: FragmentManager, item: StudentItem,
                 studentAssignmentsListener: (StudentItem)->Unit, updateListener: ()->Unit)
                = with(binding) {
            DataService.setOtherUserFacePicture(item.login, picture)
            header.text = item.login
            buttonBackgroundAssignments.setOnClickListener {
                studentAssignmentsListener(item)
            }
            buttonBackgroundAbandon.setOnClickListener{
                var dialog: AskQuestionDialogue? = null
                dialog = AskQuestionDialogue.newInstance(
                    App.getAppResources().getString(R.string.are_you_sure_to_abandon_teacher)
                ) {
                    if (it == true){
                        DataService.abandonStudyWithStudent(item.login){
                            updateListener()
                        }
                    }
                    dialog?.dismiss()
                }
                dialog?.show(fragmentManager, "")

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
        return ItemView(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.simple_layout_show_students_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items_arr.size

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        holder.bind(
            fragmentManager, items_arr[position], studentAssignmentsListener, updateListener
        )
    }
}