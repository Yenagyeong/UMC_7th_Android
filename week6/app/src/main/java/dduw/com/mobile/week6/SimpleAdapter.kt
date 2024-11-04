import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dduw.com.mobile.week6.Product
import dduw.com.mobile.week6.R

class SimpleAdapter(private val productList: List<Product>) : RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder>() {

    // ViewHolder 정의
    class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.productImage)
        val productDescription: TextView = view.findViewById(R.id.productDescription)
    }

    // onCreateViewHolder: 아이템 레이아웃을 ViewHolder로 변환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return SimpleViewHolder(view)
    }

    // onBindViewHolder: ViewHolder에 데이터 바인딩
    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        val product = productList[position]
        holder.productImage.setImageResource(product.imageResId)
        holder.productDescription.text = product.description
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int {
        return productList.size
    }
}
