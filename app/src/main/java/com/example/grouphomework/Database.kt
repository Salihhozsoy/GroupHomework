package com.example.grouphomework

object Database {

    val movies = listOf(
        Movie(
            1,
            "Titanic",
            "https://cdn.dsmcdn.com/ty79/product/media/images/20210301/11/67631570/147063869/0/0_org_zoom.jpg"
        ),
        Movie(
            2,
            "Avatar",
            "https://img-s1.onedio.com/id-52d7c61f43516df04300005f/rev-0/w-600/h-897/f-jpg/s-fcf82db0d47f693fce19cdd4106a76aebf32225c.jpg"
        ),
        Movie(
            3,
            "Passengers",
            "https://i.pinimg.com/736x/61/9c/14/619c14e20ac1bb498f37b6d12e60a7af.jpg"
        ),
        Movie(
            4,
            "Thor",
            "https://www.arttablo.com/upload/U-thor-3-film-afisi-sinema-kanvas-tablo1527774054-800.png"
        ),
        Movie(
            5,
            "Amelie",
            "https://productimages.hepsiburada.net/s/100/300-443/110000043467761.jpg"
        ),
        Movie(
            6,
            "Sherlock Holmes",
            "https://m.media-amazon.com/images/I/51+LSKG5-FL._AC_UF1000,1000_QL80_.jpg"
        ),
        Movie(7, "The Godfather", "https://img.fruugo.com/product/2/28/14590282_max.jpg"),
        Movie(
            8,
            "Spider Man",
            "https://cdn03.ciceksepeti.com/cicek/kcm68633946-1/XL/spider-man-homecoming-2017-70-cm-x-100-cm-afis-poster-janjanasa-kcm68633946-1-b98a14a32f2f4465a0992233bba4de6b.jpg"
        ),
        Movie(
            9,
            "Ayla",
            "https://upload.wikimedia.org/wikipedia/tr/archive/6/69/20171102154103%21Ayla_Film_Afi%C5%9Fi.jpg"
        ),
        Movie(10, "Pianist", "https://productimages.hepsiburada.net/s/8/375/9041599561778.jpg"),
        Movie(
            11,
            "John Wick",
            "https://www.arttablo.com/upload/U-john-wick-film-afisi-sinema-kanvas-tablo1527666076-800.jpg"
        )
    )

}
sealed class MovieListState {

    object Idle : MovieListState()
    object Loading : MovieListState()
    class Result(val movies: List<Movie>) : MovieListState()
    class Error(val throwable: Throwable) : MovieListState()
}