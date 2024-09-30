package com.example.calculadoraxml

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

 class MainActivity : AppCompatActivity(), View.OnClickListener {

     var numCount = 0;
     var operateCount = 0;
     val mixList : ArrayList<String> = ArrayList();
     lateinit var textView : TextView
     lateinit var button9: Button
     lateinit var button2: Button
     lateinit var button8: Button
     lateinit var button5: Button
     lateinit var button3: Button
     lateinit var button1: Button
     lateinit var button7: Button
     lateinit var button4: Button
     lateinit var button0: Button
     lateinit var button6: Button
     lateinit var buttonVaciar: Button
     lateinit var buttonSumar: Button
     lateinit var buttonDividir: Button
     lateinit var buttonMultiplicar: Button
     lateinit var buttonRestar: Button
     lateinit var buttonIgual: Button
     lateinit var buttonComa :Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        init()

        setClickEvent()

        cln()



    }

     fun calculate(){
         for (i in mixList.indices){
            if(i > mixList.size -1 ) break;
             if(mixList.get(i).equals("*")){
                val tmp : Long = mixList.get(i -1).toLong() * mixList.get(i + 1).toLong()
                 mixList.set(i -1, tmp.toString());
                 mixList.removeAt(i)
                 mixList.removeAt(i)

             }else if(mixList.get(i).equals("/")){
                 if(mixList.get(i + 1).equals("0"))break;
                 val tmp : Long = mixList.get(i - 1).toLong() / mixList.get(i + 1).toLong();
                 mixList.removeAt(i)
                 mixList.removeAt(i)
             }
         }
        for(i in mixList.indices){
            if(i > mixList.size - 1)break;
            if(mixList.get(i).equals("+")){
                val tmp :Long = mixList.get(i -1 ).toLong() + mixList.get(i + 1).toLong()
                mixList.set(i -1,tmp.toString())
                mixList.removeAt(i)
                mixList.removeAt(i)
            }else if(mixList.get(i).equals("-")) {
                val tmp: Long = mixList.get(i - 1).toLong() - mixList.get(i + 1).toLong()
                mixList.set(i -1,tmp.toString())
                mixList.removeAt(i)
                mixList.removeAt(i)
            }
        }
     }

     fun equal(){
         if (operateCount == 0) return
         if(operateCount == numCount){
             mixList.removeAt(mixList.size -1)
         }
         calculate()
         show(mixList)
     }

     fun addNum(num : Long){
         if (operateCount == numCount){
             numCount++
             mixList.add(num.toString())
         }else {
             val numPlus : Long = mixList.get(mixList.size - 1).toLong() * 10 + num
             mixList.set(mixList.size - 1, numPlus.toString())
         }
         show(mixList)
     }

     fun show (list : ArrayList<String>){
         if (list.size == 0) textView.setText("0")
         var sb = StringBuilder()
         for(item in list){
             sb.append(item)
         }
         textView.setText(sb)
     }
     fun cln(){
         operateCount = 0;
         numCount = 0;
         mixList.clear();
         textView.setText("0")
     }
     fun addOperate(operate:Char){
         if (numCount == 0) return
         if (operateCount < numCount){
             operateCount++
             mixList.add(operate.toString())
         }else if (operateCount == numCount){
             mixList.set(mixList.size - 1, operate.toString())
         }
         show(mixList)
     }

     fun setClickEvent(){
         button0.setOnClickListener(this)
         button1.setOnClickListener(this)
         button2.setOnClickListener(this)
         button3.setOnClickListener(this)
         button4.setOnClickListener(this)
         button5.setOnClickListener(this)
         button6.setOnClickListener(this)
         button7.setOnClickListener(this)
         button8.setOnClickListener(this)
         button9.setOnClickListener(this)
         buttonDividir.setOnClickListener(this)
         buttonMultiplicar.setOnClickListener(this)
         buttonIgual.setOnClickListener(this)
         buttonSumar.setOnClickListener(this)
         buttonVaciar.setOnClickListener(this)
         buttonRestar.setOnClickListener(this)
         buttonComa.setOnClickListener(this)
     }

     fun init() {
         textView = findViewById(R.id.textView3)
         button9 = findViewById(R.id.button13)
         button2 = findViewById(R.id.button12)
         button8 = findViewById(R.id.button10)
         button5 = findViewById(R.id.button11)
         button3 = findViewById(R.id.button15)
         button1 = findViewById(R.id.button9)
         button7 = findViewById(R.id.button7)
         button4 = findViewById(R.id.button8)
         button0 = findViewById(R.id.button19)
         button6 = findViewById(R.id.button14)
         buttonVaciar = findViewById(R.id.button)
         buttonSumar = findViewById(R.id.button18)
         buttonDividir = findViewById(R.id.button6)
         buttonMultiplicar = findViewById(R.id.button16)
         buttonRestar = findViewById(R.id.button17)
         buttonIgual = findViewById(R.id.button22)
         buttonComa = findViewById(R.id.button21)
     }

    override fun onClick(p0: View?) {
        Log.d("null","Click")
        when(p0?.id){
            R.id.button13 -> addNum(9)
            R.id.button10 -> addNum(8)
            R.id.button7 -> addNum(7)
            R.id.button14 ->addNum(6)
            R.id.button11 -> addNum(5)
            R.id.button8 -> addNum(4)
            R.id.button15 -> addNum(3)
            R.id.button12 -> addNum(2)
            R.id.button9 -> addNum(1)
            R.id.button19 -> addNum(0)
            R.id.button18 -> addOperate('+')
            R.id.button17 -> addOperate('-')
            R.id.button16 -> addOperate('*')
            R.id.button6 -> addOperate('/')
            R.id.button21 -> addOperate(',')
            R.id.button -> cln()
            R.id.button22 -> equal()


        }
    }


 }