package com.br.aula_081019

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) { //permite que a variavel permite valor nulo  e "!" n permite poder entrar valore nulo
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bntSalvar.setOnClickListener{v: View? ->
            var nomeProduto = txtProduto.text.toString()
            var preco = txtPreco.text.toString()
            var descricao =txtDescricao.text.toString()

            val sh = getSharedPreferences("produtos", Context.MODE_PRIVATE)
            sh.edit().putString(nomeProduto, preco + ";" +descricao).apply()
            Toast.makeText(this,"Produto registrado", Toast.LENGTH_SHORT).show()

            txtProduto.setText("")
            txtPreco.setText("")
            txtDescricao.setText("")
        }

        bntPesquisa.setOnClickListener{v: View? ->

            var nomeProduto = txtProduto.text.toString()
            val sh = getSharedPreferences("produtos", Context.MODE_PRIVATE)

            var resultado =sh.getString(nomeProduto, "")

            if(resultado== "") {
                Toast.makeText(this, "Produto Inexistente", Toast.LENGTH_SHORT).show()
            } else {
                val st = StringTokenizer(resultado,";")

                val preco = st.nextToken()
                val descricao = st.nextToken()

                txtPreco.setText(preco)
                txtDescricao.setText(descricao)

                Toast.makeText(this,"Produto encontrado", Toast.LENGTH_SHORT).show()

            }

        }


    }
}
