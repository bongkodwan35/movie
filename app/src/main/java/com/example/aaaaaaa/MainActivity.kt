package com.example.aaaaaaa

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    var movietList = arrayListOf<mapMovieDB>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.layoutManager = GridLayoutManager(this,2)

//LinearLayoutManager(applicationContext)

        recycler_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycler_view.addItemDecoration(
            DividerItemDecoration(recycler_view.getContext(),

            DividerItemDecoration.VERTICAL)
        )
        recycler_view.addOnItemTouchListener(object : OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {
                Toast.makeText(applicationContext,"You click on : "+ movietList[position].id,
                    Toast.LENGTH_SHORT).show()
            }
        })
    }
override fun onResume() {
    super.onResume()
    callMovieData() }
fun callMovieData(){
    movietList.clear();
    val serv : MoviesApi = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/") // Call PHP : http://10.0.2.2/movie_test/
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MoviesApi ::class.java)
    serv.retrieveMovie()
        .enqueue(object : Callback<List<mapMovieDB>> {
            override fun onResponse(call: Call<List<mapMovieDB>>, response: Response<List<mapMovieDB>>) {
                response.body()?.forEach {
                    movietList.add(mapMovieDB(it.id, it.title,it.year,it.img_movie)) }
//// Set Data to RecyclerRecyclerView
                recycler_view.adapter = MovieAdapter(movietList,applicationContext)
                tv_movie.text = "All Movie : "+ movietList.size.toString()+ " Movies"
            }
            override fun onFailure(call: Call<List<mapMovieDB>>, t: Throwable) = t.printStackTrace()
        }) } }
interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}
fun RecyclerView.addOnItemTouchListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object:

        RecyclerView.OnChildAttachStateChangeListener {

        override fun onChildViewDetachedFromWindow(view: View) {
            view?.setOnClickListener(null)
        }
        override fun onChildViewAttachedToWindow(view: View) {
            view?.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }
        }
    })

}