package com.duodinamika.week2anmp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import kotlin.properties.Delegates
import kotlin.random.Random

class GameFragment : Fragment() {
    private var total by Delegates.notNull<Int>()
    var score by Delegates.notNull<Int>()
    lateinit var btnSubmit:Button
    lateinit var num1:TextView
    lateinit var num2:TextView
    lateinit var answer:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSubmit = view.findViewById(R.id.btnSubmit);
        num1 = view.findViewById(R.id.txtNum1);
        num2= view.findViewById(R.id.txtNum2);
        answer= view.findViewById(R.id.txtAnswer)
        score=0
        generateQuestion()
        arguments?.let {
            val playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            val txtTurn = view.findViewById<TextView>(R.id.txtTurn)
            txtTurn.text = "$playerName's Turn"
        }

        btnSubmit.setOnClickListener {
            var userAnswer = answer.text.toString().toInt()
            if(userAnswer!=total){
                val action = GameFragmentDirections.actionResultFragment(score)
                Navigation.findNavController(it).navigate(action)
            }
            else{
                total = 0
                score++
                val toast = Toast.makeText(requireContext(), "Your Answer is Correct", Toast.LENGTH_SHORT)
                toast.show()
                generateQuestion()
            }

        }
    }

    fun generateQuestion(){
        val randNum1 = Random.nextInt(1, 100)
        val randNum2 = Random.nextInt(1, 100)
        num1.text = randNum1.toString()
        num2.text = randNum2.toString()
        total = randNum1 + randNum2
    }

}