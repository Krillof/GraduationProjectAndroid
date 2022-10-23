package com.example.graduationprojectandroid.fragments.for_main_page.adapters


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationprojectandroid.App
import com.example.graduationprojectandroid.R
import com.example.graduationprojectandroid.databinding.SimpleLayoutShowTeacherItemBinding
import com.example.graduationprojectandroid.fragments.AskQuestionDialogue
import com.example.graduationprojectandroid.network.DataService
import com.example.graduationprojectandroid.data.Items.TeacherItem

class ShowTeachersItemsAdapter (
    private var fragmentManager: FragmentManager,
    private var items_arr: ArrayList<TeacherItem>,
    private var teacherAssignmentsListener: (TeacherItem)->Unit,
    private var updateListener: ()->Unit
) : RecyclerView.Adapter<ShowTeachersItemsAdapter.ItemView>()
{

    class ItemView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = SimpleLayoutShowTeacherItemBinding.bind(itemView)

        @SuppressLint("SetTextI18n")
        fun bind(fragmentManager: FragmentManager, item: TeacherItem,
                 teacherAssignmentsListener: (TeacherItem)->Unit, updateListener: ()->Unit)
                = with(binding) {
            DataService.setOtherUserFacePicture(item.login, picture)
            header.text = item.login
            buttonBackgroundAssignments.setOnClickListener {
                teacherAssignmentsListener(item)
            }
            buttonBackgroundAbandon.setOnClickListener {
                var dialog: AskQuestionDialogue? = null
                dialog = AskQuestionDialogue.newInstance(
                    App.getAppResources().getString(R.string.are_you_sure_to_abandon_teacher)
                ) {
                    if (it == true){
                        DataService.abandonStudyWithTeacher(item.login) {
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
                .inflate(R.layout.simple_layout_show_teacher_item, parent, false)
        )
    }

    override fun getItemCount(): Int = items_arr.size

    override fun onBindViewHolder(holder: ItemView, position: Int) {
        holder.bind(
            fragmentManager, items_arr[position], teacherAssignmentsListener, updateListener
        )
    }
}