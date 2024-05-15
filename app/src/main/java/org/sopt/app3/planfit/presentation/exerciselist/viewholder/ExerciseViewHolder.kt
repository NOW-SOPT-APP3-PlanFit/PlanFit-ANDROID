package org.sopt.app3.planfit.presentation.exerciselist.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.app3.planfit.R
import org.sopt.app3.planfit.databinding.ItemExerciseListMuscleBinding
import org.sopt.app3.planfit.domain.model.Exercise

class ExerciseViewHolder(
    private val binding: ItemExerciseListMuscleBinding,
    private val onClick: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            onClick
        }
    }
    fun onBind(data: Exercise){
        binding.ivExerciseListMuscle.load(R.drawable.img_exc_stretching_3x)
        binding.tvExerciseListTitle.text = data.title
        binding.tvExerciseListSubtitle.text = data.description
    }
}