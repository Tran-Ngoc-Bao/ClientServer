# Xây dựng 2 mô hình client server đơn giản:
## Demo Client Server:
Chúng ta cần phải xây dựng các interface giữa các thành phần của hệ thống.

Chúng ta sẽ xây dựng một mô hình client-server đơn giản,ở đó thì client sẽ gửi một chuỗi số lên cho server. Server sẽ sắp xếp những số nhận được với việc sử dụng phương thức sort được khai báo trong interface.
## Demo RabbitMQ:
Đầu tiên cần phải cài đặt RabbitMQ về máy (link hướng dẫn: https://123host.vn/tailieu/kb/vps/huong-dan-cai-dat-rabbitmq-tren-ubuntu-20-04.html)

Client gửi yêu cầu đến hàng đợi rpc_queue và tạo ra một hàng đợi riêng của nó để chờ kết quả trả về từ Server. 

Sau khi nhận yêu cầu từ Client, Server sẽ xử lý yêu cầu và sau đó gửi trả về câu trả lời cho Client vào hàng đợi tương ứng. Server sẽ vận hành để tính chuỗi Fibonacci.
