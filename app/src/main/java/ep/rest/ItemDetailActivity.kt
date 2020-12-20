package ep.rest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.content_item_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

class ItemDetailActivity : AppCompatActivity() {
    private var item: Item = Item()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra("ep.rest.id", 0)

        if (id > 0) {
            ItemService.instance.get(id).enqueue(OnLoadCallbacks(this))
        }
    }


    private class OnLoadCallbacks(val activity: ItemDetailActivity) : Callback<Item> {
        private val tag = this::class.java.canonicalName

        override fun onResponse(call: Call<Item>, response: Response<Item>) {
            activity.item = response.body() ?: Item()

            Log.i(tag, "Got result: ${activity.item}")

            if (response.isSuccessful) {
                activity.tvItemDetail.text = activity.item.OPIS
                activity.toolbarLayout.title = activity.item.NAZIV_ARTIKEL
                activity.tvItemDetailPrice.text = String.format(Locale.ENGLISH, "%.2f EUR", activity.item.CENA)

                Picasso.get().load(activity.item.PATH_TO_IMG).into(activity.ivItemDetailPicture)


            } else {
                val errorMessage = try {
                    "An error occurred: ${response.errorBody()?.string()}"
                } catch (e: IOException) {
                    "An error occurred: error while decoding the error message."
                }

                Log.e(tag, errorMessage)
                activity.tvItemDetail.text = errorMessage
            }
        }

        override fun onFailure(call: Call<Item>, t: Throwable) {
            Log.w(tag, "Error: ${t.message}", t)
        }
    }
}

