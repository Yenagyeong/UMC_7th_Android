import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dduw.com.mobile.week6.Product
import dduw.com.mobile.week6.R

class SimpleListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SimpleAdapter

    // 15개의 상품 이미지와 설명으로 구성된 리스트 생성
    private val productList = listOf(
        Product(R.drawable.sample_image_1, "미니 1집 <17 CARAT>"),
        Product(R.drawable.sample_image_2, "미니 2집 <BOYS BE>"),
        Product(R.drawable.sample_image_3, "정규 1집 <FIRST 'LOVE&LETTER'>"),
        Product(R.drawable.sample_image_4, "정규 1집 리패키지 <Love&Letter (Repackage Album)>"),
        Product(R.drawable.sample_image_5, "미니 3집 <Going Seventeen>"),
        Product(R.drawable.sample_image_6, "미니 4집 <Al1>"),
        Product(R.drawable.sample_image_7, "미니 9집 <Attacca>"),
        Product(R.drawable.sample_image_8, "정규 2집 <TEEN, AGE>"),
        Product(R.drawable.sample_image_9, "스페셜 <DIRECTOR'S CUT>"),
        Product(R.drawable.sample_image_10, "미니 5집 <YOU MAKE MY DAY>"),
        Product(R.drawable.sample_image_11, "미니 6집 <YOU MADE MY DAWN>"),
        Product(R.drawable.sample_image_12, "정규 3집 <An Ode>"),
        Product(R.drawable.sample_image_13, "미니 7집 <헹가래>"),
        Product(R.drawable.sample_image_14, "스페셜 <Semicolon>"),
        Product(R.drawable.sample_image_15, "미니 8집 <Your Choice>")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment의 레이아웃을 inflate
        val view = inflater.inflate(R.layout.fragment_simple_list, container, false)

        // RecyclerView 설정
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = SimpleAdapter(productList)
        recyclerView.adapter = adapter

        return view
    }
}
