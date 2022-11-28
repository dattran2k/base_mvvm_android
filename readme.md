-    Các công nghệ
     - Coroutines
     - Retrofit
     - Navigation manager


- Sử dụng theo mã code để định danh fragment , hỗ trợ việc tìm kiếm
     - M00 : Main
     - M01 : Home
     - M02 : Trending
     - M03 : Discover
     - M04 : Notification
     - M05 : User

- Khi sử dụng text view thì nên sử dụng theme đã tạo, nếu ko có cái cần dùng thì tạo tương tự trong theme

- project structor
    -  base -> Mọi thứ base
    -  common -> Constants cần thiết, và các extention
    -  di -> dependency injection , chứa các module khởi tạo retrofit, preference,...
    -  helper -> Chứa các tiện ích sử dụng chung như load ảnh, log,....
    -  model -> Chứa các data class , mọi model cần thiết để vào đây, vì liên quan đến proguard mã hoá
    -  network -> Những thứ liên quan đến call api : callback,datasource , endpoint , ....
    -  presenstation -> Chứa các view
            - shareAdapter : Các adapter mà có thể dùng chung ở nhiều nơi sẽ ở đây
            - view -> Nơi chứa các màn hình
            - Ex : m01_home -> Những thứ liên quan giao diện của màn Home
               - fragment
               - viewmodel
               - state -> state để giao tiếp giữa viewmodel và fragment
               - repository -> Các hàm api cần thiết của màn Home
               - adapter -> Chứa các adapter

- git flow
  - https://danielkummer.github.io/git-flow-cheatsheet/index.vi_VN.html?fbclid=IwAR3SwkXCgQsozSAKbV9RQopQPUnxY7LycvAL_K1P7Hs2PA638Dk1ZM8u_zM