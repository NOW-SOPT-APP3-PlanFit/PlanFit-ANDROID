package org.sopt.app3.planfit.presentation.exerciseconditionlist.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.sopt.app3.planfit.databinding.ItemExerciseConditionListBinding
import org.sopt.app3.planfit.domain.model.ExerciseCondition

class ExerciseConditionListViewHolder (
    private val selectedCondition: String?,
    private val binding: ItemExerciseConditionListBinding,
    private val onClick:(String) -> Unit
) : RecyclerView.ViewHolder(binding.root){
    fun onBind(data: ExerciseCondition){
        binding.apply {
            if(selectedCondition == data.title){
                vExerciseListEdge.isVisible =true
            }
            exerciseListConditionTitle.text = data.title
            exerciseListCondition.text = data.condition
            root.setOnClickListener {
                onClick(data.title)
            }
        }
    }
}