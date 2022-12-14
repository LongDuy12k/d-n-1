CREATE DATABASE MYVNTOUR
USE MYVNTOUR

IF OBJECT_ID('LOAIHINH') IS NOT NULL
DROP TABLE LOAIHINH
GO
CREATE TABLE LOAIHINH 
(
	MaLH	 INT IDENTITY(1,1) PRIMARY KEY,
	TenLH	 NVARCHAR(50) NOT NULL
)

IF OBJECT_ID('KHACHSAN') IS NOT NULL
DROP TABLE KHACHSAN
GO
CREATE TABLE KHACHSAN 
(
	MaKS	      INT IDENTITY(1,1) PRIMARY KEY,
	MaLH	      INT NOT NULL REFERENCES LOAIHINH(MaLH),
	TenKS	      NVARCHAR(200) NOT NULL,
	Hang	      INT NOT NULL,
	ChinhSachVS   INT NULL,
	DiaDiem       NVARCHAR(500) NOT NULL,
	Longitude     NVARCHAR(150) NOT NULL,
	Latitude      NVARCHAR(150) NOT NULL,
	TimeDat	      NVARCHAR(10) NOT NULL,
	TimeTra	      NVARCHAR(10) NOT NULL,
	SoLuongP      INT NOT NULL,
	MoTa	      NVARCHAR(MAX) NULL,
	GiaDD		  INT NOT NULL
)
IF OBJECT_ID('CHUKHACHSAN') IS NOT NULL
DROP TABLE CHUKHACHSAN
GO
CREATE TABLE CHUKHACHSAN
(
	MaBoss	 INT IDENTITY(1,1) PRIMARY KEY,
    MaKS	 INT NOT NULL REFERENCES KHACHSAN(MaKS),
	Anh		 IMAGE NULL,
	Ten	     NVARCHAR(100) NULL,
	SDT	     NVARCHAR(15) NULL
)

IF OBJECT_ID('HINHANH') IS NOT NULL
DROP TABLE HINHANH
GO
CREATE TABLE HINHANH 
(
	MaHA	 INT IDENTITY(1,1) PRIMARY KEY,
    MaKS	 INT NOT NULL REFERENCES KHACHSAN(MaKS),
	HinhAnh	 VARBINARY(MAX) NULL
)

IF OBJECT_ID('TIENNGHIDV') IS NOT NULL
DROP TABLE TIENNGHIDV 
GO
CREATE TABLE TIENNGHIDV 
(
	ID_DV	   INT IDENTITY(1,1) PRIMARY KEY,
	MaKS	   INT NOT NULL REFERENCES KHACHSAN(MaKS),
	TenDV	   NVARCHAR(200) NULL, 
	MoTa	   NVARCHAR(MAX) NULL
)

IF OBJECT_ID('TIENNGHIHD') IS NOT NULL
DROP TABLE TIENNGHIHD 
GO
CREATE TABLE TIENNGHIHD 
(
	ID_TNHD	   INT IDENTITY(1,1) PRIMARY KEY,
	MaKS	   INT NOT NULL REFERENCES KHACHSAN(MaKS),
	WifiSanh   INT NULL,
	WifiPhong  INT NULL,
	BeBoi      INT NULL,
	DauXe      INT NULL,
	Spa		   INT NULL,
	VatNuoi    INT NULL,
	DieuHoa    INT NULL,
	NhaHang    INT NULL,
	Bar        INT NULL,
	Gym        INT NULL
)


IF OBJECT_ID('PHONG') IS NOT NULL
DROP TABLE PHONG 
GO
CREATE TABLE PHONG 
(
	MaPhong	   INT IDENTITY(1,1) PRIMARY KEY,
	MaKS	   INT NOT NULL REFERENCES KHACHSAN(MaKS),
	TenPhong   NVARCHAR(100) NOT NULL,
	SoPhong    INT NOT NULL,
	DienTich   FLOAT NULL,
	Gia        MONEY NOT NULL,
	SoGiuong   INT NOT NULL,
	NguoiLon   INT NOT NULL,
	TreNho     INT NOT NULL,
	MoTa       NVARCHAR(MAX) NULL
)

IF OBJECT_ID('TIENNGHIPHONG') IS NOT NULL
DROP TABLE TIENNGHIPHONG
GO
CREATE TABLE TIENNGHIPHONG 
(
	MaTN			INT IDENTITY(1,1) PRIMARY KEY,
    MaPhong			INT NOT NULL REFERENCES PHONG(MaPhong),
	DieuHoa			INT NOT NULL,
	TV				INT NOT NULL,
	KetAnToan		INT NOT NULL,
	TuLanh			INT NOT NULL,
	Bep				INT NOT NULL,
	Ban				INT NOT NULL,
	WifiFree		INT NOT NULL,
	DichVuPhong		INT NOT NULL,
	MayGiat			INT NOT NULL,
	MaySayToc		INT NOT NULL,
	BanUi			INT NOT NULL,
	KhongHutThuoc   INT NOT NULL,
	BonTamSpa	    INT NOT NULL
)


IF OBJECT_ID('HINHANHPHONG') IS NOT NULL
DROP TABLE HINHANHPHONG
GO
CREATE TABLE HINHANHPHONG 
(
	MaHA	 INT IDENTITY(1,1) PRIMARY KEY,
    MaPhong	 INT NOT NULL REFERENCES PHONG(MaPhong),
	HinhAnh	 VARBINARY(MAX) NULL
)

IF OBJECT_ID('KHACHHANG') IS NOT NULL
DROP TABLE KHACHHANG 
GO
CREATE TABLE KHACHHANG 
(
	MaKH	   INT IDENTITY(1,1) PRIMARY KEY,
	Email	   NVARCHAR(50) NOT NULL,
	UserName   NVARCHAR(50) NOT NULL,
	Pass       NVARCHAR(50) NOT NULL,
	Anh		   IMAGE NULL,
	DiaChi     NVARCHAR(500) NULL,
	SDT        NVARCHAR(10) NULL,
	GioiTinh   INT NULL,
	CMND       NVARCHAR(13) NULL,
	ISADMIN    INT NULL
)
IF OBJECT_ID('CHECKTT') IS NOT NULL
DROP TABLE CHECKTT
GO
CREATE TABLE CHECKTT
(
	MaTT		INT IDENTITY(1,1) PRIMARY KEY,
    MaKS		INT NOT NULL REFERENCES KHACHSAN(MaKS),
	MaKH		INT NOT NULL REFERENCES KHACHHANG(MaKH),
	TrangThai	INT NOT NULL
)

IF OBJECT_ID('PHIEUTHUEPHONG') IS NOT NULL
DROP TABLE PHIEUTHUEPHONG
GO
CREATE TABLE PHIEUTHUEPHONG 
(
	MaPT	    INT IDENTITY(1,1) PRIMARY KEY,
	MaKH        INT NOT NULL REFERENCES KHACHHANG(MaKH),
	MaPhong	    INT NOT NULL REFERENCES PHONG(MaPhong),
	SoNguoiThue INT NOT NULL,
	NgayDen     DATE NOT NULL,
	NgayDi      DATE NOT NULL,
	HoTen		NVARCHAR(50),
	SDT			NVARCHAR(50),
	Email		NVARCHAR(50),
	YeuCauDB	NVARCHAR(500)
)

select * from LOAIHINH
delete from LOAIHINH
DBCC CHECKIDENT ('LOAIHINH', RESEED, 0)
insert into LOAIHINH
values  ('Hotel'),
		('Vila'),
		('Resort'),
		(N'Chung cư'),
		('Homestay')

select * from KHACHSAN
delete from KHACHSAN
DBCC CHECKIDENT ('KHACHSAN', RESEED, 0)
insert into KHACHSAN
values	(1,'Charming Hotel',4,1,N'15 Yên Thái, Hàng Gai, Hoàn Kiếm, Hà Nội, Việt Nam','105.8455923','21.0318984','14:00','12:00',20,N'Khách sạn bình dân nằm trên con phố nhỏ thuộc phường Hàng Gai, cách hồ Hoàn Kiếm 1 km và cách Nhà hát lớn Hà Nội 2 km. Phòng ở bài trí ấm cúng, có Wi-Fi miễn phí, TV màn hình phẳng, máy tính cá nhân, tủ lạnh nhỏ và máy pha cà phê.
Một số phòng hướng ra phố. Khách sạn phục vụ ăn uống tại phòng 24 giờ','250000'),
		(3,'Risemount Premier Resort',5,1,N'120 Nguyễn Văn Thoại, Bắc Mỹ An, Ngũ Hành Sơn, Đà Nẵng, Việt Nam','108.2398911','16.0546179','15:00','12:00',21,N'Trải nghiệm dịch vụ đẳng cấp thế giới ở Risemount Premier Resort Da Nang. Nằm ở khu Bãi biển Mỹ Khê thuộc thành phố Đà Nẵng, cách Bãi biển Mỹ An 500 m, Risemount Resort Danang có 2 hồ bơi ngoài trời và tiện nghi BBQ. Resort này cũng có trung tâm thể dục và trung tâm spa với bể sục cùng phòng xông hơi khô. Phòng nghỉ gắn máy điều hòa tại resort được bố trí khu vực ghế ngồi, TV truyền hình vệ tinh màn hình phẳng và phòng tắm riêng với đồ vệ sinh cá nhân miễn phí. Một số phòng còn có ban công riêng, bếp hoặc WiFi miễn phí. Risemount Resort Danang có trung tâm dịch vụ doanh nhân cho khách tùy ý sử dụng. Nhân viên tại quầy lễ tân phục vụ 24 giờ/ngày có thể tư vấn cho khách về khu vực. Du khách có thể thưởng thức bữa sáng đầy đủ kiểu Anh/Ailen tại nhà hàng hay đồ uống tại quầy bar.','1856000'),
		(1,'Conifer Grand',3,1,N'44 Thọ Xương, Hàng Trống, Hoàn Kiếm, Hà Nội, Việt Nam','105.848263','21.0287823','14:00','12:00',40,N'Nằm cách Sân bay Quốc tế Nội Bài 26 km, khách sạn sang trọng này cách Đền Ngọc Sơn 13 phút đi bộ và cách Nhà hát lớn Hà Nội 17 phút đi bộ.
Phòng ốc tinh tế, bài trí ấm cúng, có Wi-Fi miễn phí, TV màn hình phẳng, dụng cụ pha cà phê, két an toàn và tủ lạnh nhỏ. Phòng nâng hạng có thêm nhà bếp và không gian ngồi thư giãn. Một số phòng hướng ra thành phố. Phục vụ bữa sáng tự chọn miễn phí. Các tiện nghi gồm có khu spa và phòng tập thể dục. Có dịch vụ đưa đón sân bay','745600'),
		(3,'Dusit Princess Moonrise Beach',5,1,N'Trần Hưng Đạo, Tổ 2, Ấp Cửa Lấp, Thị trấn Dương Đông, Phú Quốc, Tỉnh Kiên Giang, Việt Nam','103.96760276931289','10.182160027765066','15:00','12:00',8,N'Nằm trên đảo Phú Quốc, cách Bãi Dài 60 m, Dusit Princess Moonrise Beach Resort cung cấp chỗ nghỉ với nhà hàng, chỗ đỗ xe riêng miễn phí, hồ bơi ngoài trời và trung tâm thể dục. Chỗ nghỉ này có khu vực bãi biển riêng, quán bar và vườn. Chỗ nghỉ cũng có lễ tân 24 giờ, CLB trẻ em và dịch vụ thu đổi ngoại tệ cho khách','1958000'),
		(3,'Eden Resort',4,1,N'Ấp Cửa lấp, Xã Dương Tơ, H. Phú Quốc, Thị trấn Dương Đông, Phú Quốc, Tỉnh Kiên Giang, Việt Nam','103.966854717','10.179236','14:00','12:00',85,N'Tọa lạc tại Đảo Ngọc xinh đẹp, Eden Resort Phú Quốc nổi bật trong không gian xanh rộng 18000m2 với phong cách kiến trúc Pháp hào hoa và lãng mạn. Eden Resort Phú Quốc gồm có 85 phòng nghỉ và bungalow đạt tiêu chuẩn 4 sao được thiết kế đặc biệt tinh tế và khéo léo','2216345'),
		(3,'Nam Nghi',5,1,N'Tổ 5, Ấp Lê Bát, Cửa Cạn, xã Cửa Cạn, Phú Quốc, Tỉnh Kiên Giang, Việt Nam','103.8587035','10.3042473','14:00','12:00',45,N'Nằm cách thị trấn Dương Đông – Phú Quốc khoảng 25km, phía Bắc Đảo. Nếu Phú Quốc được thiên nhiên ban tặng rất nhiều ưu ái thì Nam Nghi Resort ở Mũi Móng Tay – Phú Quốc là nơi được ban tặng nhiều nhất từ thiên nhiên. Nam Nghi Phu Quoc Island Resort là nơi không thể bỏ qua cho những ai yêu thiên nhiên và muốn tìm đến để được trải nghiệm cảm giác như gắn kết; như là cầu nối giữa biển cả và rừng xanh; với những cây cổ thụ cao vút, với những tảng cây rừng nhiều kiểu dáng sinh động lan ra tận mép nước.','4040000'),
		(3,'VinOasis',5,1,N'Bãi Dài, Gành Dầu, xã Gành Dầu, Phú Quốc, Tỉnh Kiên Giang, Việt Nam','103.8533738','10.3299235','14:00','12:00',1300,N'Vinpearl VinOasis Phú Quốc Resort nằm ở phía Bắc đảo Phú Quốc, tọa lạc tại Bãi Dài, Vinpearl VinOasis Phú Quốc Resort cách khu vui chơi Vinpearl Land Phú Quốc tầm 800m & Vinpearl Safari tầm 4,4km, cách thị trấn Dương Đông khoảng 21km, cách sân bay Phú Quốc tầm 32,4km.','1601000'),
		(1,'Hanoi Crystal',3,1,N'9 Hàng Thùng, Lý Thái Tổ, Hoàn Kiếm, Hà Nội, Việt Nam','105.853059','21.032314','14:00','12:00',37,N'Khách sạn thư thái này nằm bên trong Khu Phố Cổ với nhiều cửa hàng và nhà hàng bao quanh, cách Hoàng Thành Thăng Long 3 km và cách ga xe lửa Gia Lâm 5 km. Phòng ở bài trí hợp lý trang bị Wi-Fi miễn phí và TV màn hình phẳng; còn phòng gia đình cho phép lưu trú tối đa 4 người. Khách sạn có dịch vụ phòng 24/7','1850200'),
		(1,'Hanoi Gatsby',3,1,N'67 Hàng Thiếc, Hàng Gai, Hoàn Kiếm, Hà Nội','105.845586','21.032665','14:00','12:00',35,N'Tọa lạc ở thành phố Hà Nội, nằm trong bán kính 700 m từ Chợ Đồng Xuân, Hanoi Gatsby Hotel có dịch vụ nhận phòng/trả phòng cấp tốc, phòng nghỉ không gây dị ứng, nhà hàng, WiFi miễn phí và quầy bar. Khách sạn 3 sao này còn có dịch vụ concierge và bàn đặt tour. Chỗ nghỉ cũng cung cấp dịch vụ lễ tân 24 g
iờ, dịch vụ phòng và dịch vụ thu đổi ngoại tệ.','499000'),
		(1,'Golden Silk Boutique',4,1,N'111 Hàng Gai, Quận Hoàn Kiếm, Hà Nội, Việt Nam','105.8471854','21.0310292','14:00','12:00',55,N'Khách sạn Golden Silk Boutique tọa lạc tại trung tâm thành phố, trong khu vực phố cổ, chỉ cách hồ Hoàn Kiếm xinh đẹp, nhà hát múa rối Thăng Long và nhà thờ lớn chưa đầy 3 phút đi bộ. Với 55 phòng nghỉ sang trọng được thiết kế và trang bị nội thất hiện đại cùng đội ngũ nhân viên chuyên nghiệp và thân thiện chắc chắn sẽ là lựa chọn hoàn hảo cho quý khách khi có dịp tới thăm thủ đô Hà Nội ngàn năm văn hiến','613158'),
		(1,'Imperial Hotel',4,1,N'44 Hàng Hành, Phường Hàng Trống, Quận Hoàn Kiếm, Hà Nội, Việt Nam','105.847629','21.0311661','14:00','12:00',43,N'Khách sạn yên bình này nằm giữa các nhà hàng và quán cà phê trong khu phố cổ, cách đền Ngọc Sơn 7 phút đi bộ và Lăng Chủ tịch Hồ Chí Minh 3 km.Phòng ở thoáng mát, trang bị tủ lạnh nhỏ, TV truyền hình vệ tinh, két an toàn, Wi-Fi miễn phí, dụng cụ pha trà và cà phê. Một số phòng cao cấp có thêm ban công và/hoặc bể sục. Phục vụ miễn phí bữa sáng tự chọn tại nhà hàng trên tầng thượng','672794'),
		(3,'LAzure Resort and Spa',4,1,N'64 Trần Hưng Đạo, Dương Đông, Phú Quốc, Kiên Giang, Thị trấn Dương Đông, Phú Quốc, Tỉnh Kiên Giang, Việt Nam','103.9598508','10.2076887','14:00','12:00',55,N'LAzure Resort and Spa là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại LAzure Resort and Spa. LAzure Resort and Spa là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ. Một trong những đặc điểm chính của khách sạn này là các liệu pháp spa đa dạng. Hãy nâng niu bản thân bằng các liệu pháp thư giãn, phục hồi giúp quý khách tươi trẻ. Khi lưu trú tại nơi nghỉ thì nội thất và kiến trúc hẳn là hai yếu tố quan trọng khiến quý khách mãn nhãn. Với thiết kế độc đáo, LAzure Resort and Spa mang đến không gian lưu trú làm hài lòng quý khách. Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại LAzure Resort and Spa, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân','3501000'),
		(1,'Sofitel Legend Metropole',5,1,N'15 P. Ngô Quyền, Street, Hoàn Kiếm, Hà Nội, Việt Nam','105.848041','21.030679','14:00','12:00',364,N'Khách sạn sang trọng với kiến trúc tân cổ điển này tọa lạc trên con đường có những hàng cây và dãy cửa hàng, cách Nhà hát Lớn Hà Nội 5 phút đi bộ và Sân bay Quốc tế Nội Bài 27 km. Phòng ốc sang trọng với sàn gỗ cứng, Wi-Fi miễn phí, TV màn hình phẳng, dụng cụ pha trà và cà phê. Phòng club thoáng mát có thêm dịch vụ quản gia 24 giờ, phòng chờ phục vụ bữa sáng, trà chiều và cocktail miễn phí. Phòng suite lộng lẫy có thêm khu tiếp khách. Ăn uống có nhiều lựa chọn cao cấp như nhà hàng kiểu Pháp và Việt, cùng 3 quầy bar sang trọng. Có khu spa sang trọng, bể bơi nước nóng ngoài trời, phòng tập thể dục và vườn cây lộng lẫy','2866500'),
		(1,'The Palmy Hotel & Spa',4,1,N'4A-4B Bảo Khánh, Phường Hàng Trống, Quận Hoàn Kiếm, Hà Nội, Việt Nam','105.848041','21.030679','14:00','12:00',55,N'Chúng tôi cũng tự hào về dịch vụ spa của mình đã được chứng nhận bởi Thann Spa nổi tiếng, được tạp chí Condé Nast Traveler bình chọn trong Top 55 Spa tốt nhất thế giới năm 2006, Spa của chúng tôi tuân theo triết lý của Thann, thể hiện một loạt các liệu pháp spa dựa trên nghệ thuật trị liệu tự nhiên của nó. Là spa trong khách sạn duy nhất ở Old Quarters, chúng tôi không chỉ cung cấp dịch vụ chăm sóc sức khỏe, trẻ hóa mà còn mang đến sự tiện lợi tối ưu cho khách hàng.phong binh dan','777000'),
		(4,'Mangala Zen Garden & Luxury Apartments',5,1,N'J.02 The Ocean Villas, Truong Sa Road, Ward Ngu Hanh Son, Da Nang, Đà Nẵng, Việt Nam','108.2479267','15.9760149','15:00','14:00',60,N'Trải nghiệm dịch vụ đẳng cấp thế giới ở Mangala Zen Garden & Luxury Apartments. Tọa lạc trong khu phức hợp CLB chơi golf Đà Nẵng thuộc thành phố ven biển Đà Nẵng, Mangala Zen Garden & Luxury Apartments có 2 hồ bơi ngoài trời và 2 nhà hàng phục vụ chế độ ăn ayurvedic (ăn kiêng theo kiểu Ấn Độ) cũng như một loạt món ăn Á-Âu. Chỗ nghỉ này có khu vực bãi biển riêng dành cho khách. Đà Nẵng là nơi hoàn hảo để khách tham gia nhiều hoạt động như bơi lội, thể thao dưới nước và đi dã ngoại. Mangala Zen Garden & Luxury Apartments nằm cách sân golf Montgomerie Links 900 m và Phố Cổ Hội An 12 km. Sân bay quốc tế Đà Nẵng cách đó 15 km','2337000'),
		(1,'Novotel Danang Premier Han River',5,1,N'36 Bach Dang Thach Than Ward, Hai Chau District, Thạch Thang, Quận Hải Châu, Đà Nẵng, Việt Nam','108.2213843','16.0773534','15:00','12:00',323,N'Tận hưởng dịch vụ đỉnh cao, đẳng cấp thế giới tại Novotel Danang Premier Han River. Tọa lạc trên bờ phía Tây của Sông Hàn tại trung tâm thành phố Đà Nẵng, khách sạn 37 tầng này cung cấp các phòng nghỉ hiện đại chỉ cách Ga Đà Nẵng 1 km và cách Sân bay Quốc tế Đà Nẵng 2 km. Wi-Fi được cung cấp miễn phí cho khách. Tòa nhà chọc trời này cho tầm nhìn ra toàn cảnh thành phố và các bãi biển từ sảnh khách. Khách có thể bơi lội thư giãn tại hồ bơi ngoài trời hay sử dụng tiện nghi spa và tập thể dục trong khuôn viên','1719023'),
		(1,'Muong Thanh Luxury Song Han Hotel',4,1,N'115 Nguyễn Văn Linh, Phường Phước Ninh, Nam Dương, Quận Hải Châu, Đà Nẵng, Việt Nam','108.2108084','16.0599394','14:00','12:00',201,N'Muong Thanh Luxury Song Han Hote nằm ở Quận Hải Châu thuộc thành phố Đà Nẵng. Khách sạn này có truy cập Internet miễn phí, hồ bơi ngoài trời và nhà hàng. Các hoạt động tại đây bao gồm karaoke và bi-da. Mỗi phòng nghỉ gắn máy điều hòa đều được trang bị truyền hình vệ tinh, minibar và máy pha trà/cà phê. Các phòng tắm riêng đi kèm bồn tắm và vòi sen. Khách sạn có trung tâm thể dục, sân chơi cho trẻ em và tiện nghi BBQ. Spa cung cấp dịch vụ mát-xa. Du khách có thể thưởng thức ẩm thực quốc tế và địa phương tại nhà hàng của khách sạn. Mường Thanh Luxury Song Han Hotel nằm cách sân bay quốc tế Đà Nẵng 1,5 km. Khách sạn cung cấp dịch vụ đưa đón sân bay 1 chiều miễn phí cho tất cả khách lưu trú trong các Phòng Deluxe, Suite và Phòng Premier Deluxe','831600'),
		(3,'InterContinental Danang Sun Peninsula Resort',5,1,N'Bãi Bắc, Bán Đảo Sơn Trà, Thọ Quang, Sơn Trà, Đà Nẵng, Việt Nam','108.3049876','16.1198314','15:00','12:00',180,N'Tự hào cung cấp các lựa chọn ăn uống tuyệt hảo như La Maison 1888 danh tiếng, InterContinental Danang Sun Peninsula Resort đem đến cho du khách nơi lánh mình sang trọng với bãi biển riêng, hồ bơi lớn ngoài trời và spa. Du khách có thể ngắm nhìn quang cảnh biển tuyệt đẹp từ resort. Toàn bộ chỗ ở tại đây đều được bài trí sang trọng và có ban công riêng. Các biệt thự bên bờ biển của resort nằm ẩn mình giữa những tảng đá hùng vĩ tại chân núi và có hồ bơi riêng, hiên tắm nắng cùng các tiện nghi phòng tắm sang trọng. Một số phòng chọn lọc đi kèm bồn tắm spa và hiên ăn uống riêng nhìn ra quang cảnh biển tuyệt đẹp','7636879'),
		(1,'Altara Suites by Ri-Yaz',4,1,N'120 Võ Nguyên Giáp, Phước Mỹ, Sơn Trà, Đà Nẵng, Việt Nam','108.2431522','16.0777255','15:00','14:00',155,N'Altara Suites cung cấp chỗ nghỉ bên bờ biển tại thành phố Đà Nẵng, cách Bãi biển Mỹ Khê 200 m. Chỗ nghỉ này có hồ bơi ngoài trời trên sân thượng. Du khách có thể thưởng thức bữa ăn kiểu à la carte tại nhà hàng Altitude hoặc nhâm nhi đồ uống ở quầy bar. Chỗ nghỉ cung cấp WiFi miễn phí trong toàn bộ khuôn viên. Tất cả phòng nghỉ tại đây đều có ban công, truyền hình cáp màn hình phẳng, khu vực ăn uống và bếp được trang bị lò vi sóng cùng tủ lạnh. Ngoài ra, phòng còn có bếp nấu, ấm đun nước cũng như phòng tắm riêng với áo choàng tắm và dép đi trong phòng. Khăn tắm và dép đi trong phòng cũng được cung cấp cho khách. Du khách có thể tích cực rèn luyện sức khỏe tại trung tâm thể dục trên tầng 32 sau đó thư giãn ở phòng xông hơi khô miễn phí nhìn ra Bán đảo Sơn Trà','1147500'),
		(2,'The Choai Villa Sóc Sơn',4,1,N'Thôn Lâm Trường, Xã Minh Phú, Huyện Sóc Sơn','105.7746400','21.265467','14:00','12:00',5,N'The Choai Villa được xây dựng tại Thôn Trường Lâm, Sóc Sơn, một trong những góc 
tĩnh lặng của huyện nhỏ ngoại thành. Đến đây, bạn như trở về với giấc mơ trưa bởi 
vẻ tĩnh mịch, những làn gió trong lành thoáng qua và cả khung cảnh thiên nhiên đến 
nao lòng người. Giữa khoảng trời bao la, giữa tiếng gọi của thiên nhiên. 
Chắc chắn bạn sẽ có được một kì nghỉ dưỡng đáng nhớ và tuyệt vời nhất. ',1200000),
		(2,'Pine Forest Villa',5,1,N'Thôn Lâm Trường, Minh Phú, Sóc Sơn','105.7862656','21.2938875','14:00','12:00',4,N'Pine Forest Villa là một địa điểm nghỉ dưỡng gần Hà Nội với đầy đủ tiện nghi như: Hồ bơi, khu vực tiệc nướng, hay vẻ đẹp thiên nhiên của rừng thông. Đặc biệt, khi tối đến, những ánh đèn led được thắp trên những thân cây làm sáng bừng không gian',4500000),
		(2,'Hill Top Villa',5,1,N'Tiến Xuân, Thạch Thất, Hà Nội','105.4762371','20.9923907','14:00','12:00',2,N'Là villa gần Hà Nội có vị trí trên một ngọn đồi cao nhất, Hill Top Villa đã dễ dàng thu hút cả làng du lịch Hà Nội tới check-in và nghỉ dưỡng. Thức dậy tại Hill Top bạn có thể cảm nhận rõ nhịp thở của rừng núi Hòa Lạc, chiều về ngẩn ngơ ngắm hoàng hôn buông. Chắc chắn đây sẽ là một căn villa nghỉ dưỡng gần Hà Nội đầy lý tưởng vào dịp cuối tuần',5000000),
		(3,'Movenpick Resort Cam Ranh',5,1,N'Plot D12, Cam Hai Dong, Cam Hải Đông, Nha Trang, Khánh Hòa, Việt Nam','109.2059897','12.0455807','14:00','12:00',50,N'Dịch vụ thượng hạng song hành với hàng loạt tiện nghi phong phú sẽ đem đến cho quý khách trải nghiệm của một kỳ nghỉ viên mãn nhất. Trung tâm thể dục của khách sạn là một trong những tiện nghi không thể bỏ qua khi lưu trú tại đây.Hưởng thụ một ngày thư thái đầy thú vị tại hồ bơi dù quý khách đang du lịch một mình hay cùng người thân.Nhận ưu đãi đặc biệt dành cho các liệu pháp spa tinh tuý nhất giúp thư giãn tinh thần và làm tươi trẻ cơ thể.
Quầy tiếp tân 24 giờ luôn sẵn sàng phục vụ quý khách từ thủ tục nhận phòng đến trả phòng hay bất kỳ yêu cầu nào. Nếu cần giúp đỡ xin hãy liên hệ đội ngũ tiếp tân, chúng tôi luôn sẵn sàng hỗ trợ quý khách.
Movenpick Resort Cam Ranh là khách sạn sở hữu đầy đủ tiện nghi và dịch vụ xuất sắc theo nhận định của hầu hết khách lưu trú.
Hãy sẵn sàng đón nhận những giây phút vô giá khó phai trong suốt kỳ nghỉ của quý khách tại Movenpick Resort Cam Ranh',1489111),
		(3,'Amiana Resort Nha Trang',5,1,N'Vịnh Nha Trang, Đường Phạm Văn Đồng, Nha Trang, Khánh Hòa, Việt Nam','109.2174855','12.2970691','14:00','12:00',40,N'Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Amiana Resort Nha Trang là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Amiana Resort Nha Trang
Amiana Resort Nha Trang là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
Một trong những đặc điểm chính của khách sạn này là các liệu pháp spa đa dạng. Hãy nâng niu bản thân bằng các liệu pháp thư giãn, phục hồi giúp quý khách tươi trẻ thân, tâm.Khi lưu trú tại nơi nghỉ thì nội thất và kiến trúc hẳn là hai yếu tố quan trọng khiến quý khách mãn nhãn. Với thiết kế độc đáo, Amiana Resort Nha Trang mang đến không gian lưu trú làm hài lòng quý khách.
Từ sự kiện doanh nghiệp đến họp mặt công ty, Amiana Resort Nha Trang cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Amiana Resort Nha Trang, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Nếu dự định có một kỳ nghỉ dài, thì Amiana Resort Nha Trang chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Amiana Resort Nha Trang sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Khách sạn này là nơi tốt nhất dành cho những ai mong muốn một nơi thanh bình, thư thái để ẩn mình khỏi đám đông ồn ã, xô bồ',1852000),
		(3,N'Vinpearl Phú Quốc',5,1,N'Bãi Dài, Gành Dầu, Vũng Bàu, xã Gành Dầu, Phú Quốc, Tỉnh Kiên Giang, Việt Nam','103.8449136','10.3280973','14:00','12:00',60,N'Không chỉ sở hữu vị trí giúp quý khách dễ dàng ghé thăm những địa điểm lý thú trong chuyến hành trình, Vinpearl Resort & Spa Phu Quoc cũng sẽ mang đến cho quý khách trải nghiệm lưu trú mỹ mãn.
Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Vinpearl Resort & Spa Phu Quoc là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
Vinpearl Resort & Spa Phu Quoc là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ. Từ sự kiện doanh nghiệp đến họp mặt công ty, Vinpearl Resort & Spa Phu Quoc cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Vinpearl Resort & Spa Phu Quoc, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.
Khách sạn này là lựa chọn lý tưởng cho cả du khách chơi golf nghiệp dư lẫn chuyên nghiệp.',2001600),
		(3,'Resort Radisson Blue',5,1,N'Bãi Dài, Gành Dầu, xã Gành Dầu, Phú Quốc, Tỉnh Kiên Giang, Việt Nam','103.8514797','10.3280974','14:00','12:00',60,N'Phù hợp cho cả những chuyến công tác và du lịch nghỉ mát, Radisson Blu Resort Phú Quốc nằm ở một vị trí lý tưởng tại Gành Dầu, một trong những địa điểm ruột của người dân bản địa. Từ đây, thật dễ dàng để trải nghiệm mọi khía cạnh của thành phố sống động này. Chỗ nghỉ có vị trí rất thuận tiện, chỉ cách trung tâm thành phố 22 km và cách sân bay Quốc tế Phú Quốc 24,2 km, du khách có thể thoải mái tham quan, khám phá các điểm du lịch nổi tiếng như: Suối Đá Bàn, Công viên giải trí Vinpearl Land Phú Quốc, Bãi biển Gành Dầu',2601600),
		(1,'Mercure Hanoi La Gare',4,1,N'94 Ly Thuong Kiet Street Hoan, Hoan Kiem District, Cửa Nam, Quận Hoàn Kiếm, Hà Nội, Việt Nam','105.83958','21.0261084','14:00','12:00',60,N'Mercure Hanoi La Gare là 1 khách sạn 4 sao tọa lạc tại điểm giao giữa phố cổ và trung tâm thành phố. 
Cách nhà ga 5 phút đi bộ, cách hồ Hoàn Kiếm 2km và sân bay khoảng 30km. Kiến trúc của khách sạn được 
thiết kế theo phong cách thuộc địa pháp, kết hợp với nội thất hiện đại. Mercure Hanoi La Gare cung
 cấp hệ thống phòng nghỉ hiện đại, tiện nghi cao cấp. Là một điểm lưu trú lý tưởng khi đến với Hà Nội',11463933),
		(1,N'Mayflower Hà Nội',3,1,N'11 Hàng Rươi, Hàng Mã, Quận Hoàn Kiếm, Hà Nội, Việt Nam','105.8458879','21.0369653','14:00','12:00',60,N'May Flower Hotel là một lựa chọn tuyệt vời cho khách du lịch khi đến thăm Hà Nội, mang đến không khí dành cho gia đình cùng với những tiện nghi hữu ích cho suốt thời gian lưu trú của bạn',212750),
		(1,N'Mövenpick Hà Nội',5,1,N'83A Lý Thường Kiệt, Trần Hưng Đạo, Quận Hoàn Kiếm, Hà Nội, Việt Nam','105.8393218','21.036965','14:00','12:00',60,N'Tọa lạc tại một vị trí thuận tiện ngay giữa trung tâm thương mại Hà Nội, khách sạn cách Sân bay Quốc tế
 Nội Bài khoảng 40 phút lái xe và cách trung tâm thành phố chỉ 5 phút. Với kiến ​​trúc Pháp đặc trưng, ​​
dịch vụ và tiện nghi hoàn hảo, Khách sạn Mövenpick Hà Nội được thiết kế đặc biệt để đáp ứng nhu cầu của khách đoàn.',1624000),
		(1,'Hanoi La Storia',3,1,N'45 Hàng Đồng, Hàng Bồ, Quận Hoàn Kiếm, Hà Nội, Việt Nam','105.8455732','21.0358127','14:00','12:00',80,N'Khách sạn La Storia Hà Nội tọa lạc trên một trong vài con phố yên tĩnh còn sót lại ở trung tâm Phố Cổ, có thể đi bộ đến Hồ Hoàn Kiếm, 
Đền Ngọc Sơn, Nhà hát Lớn Hà Nội và Nhà hát Múa rối Nước Thăng Long nổi tiếng.  
Chỉ vài bước chân từ cửa nhà chúng tôi, bạn sẽ được tận hưởng các điểm tham quan của Chợ đêm Hà Nội trên Hàng Ngang và Hàng Đào 
(các tối thứ Sáu, thứ Bảy và Chủ nhật hàng tuần) và Nhà cổ Hà Nội trên phố Mã Mây, cũng như toàn bộ trung tâm mua sắm và tiện ích ngân hàng. 
Chúng tôi cũng mang đến cho bạn tất cả những tiện ích như thang máy, nhà hàng và những tiện nghi khác như mong đợi từ một khách sạn 3 sao.
 Cho dù bạn cần phòng 2 giường đơn, phòng đôi hay phòng gia đình, bạn đều có thể tìm thấy một giá trị tuyệt vời đồng tiền của mình
 tại Khách sạn La Storia, ngôi nhà ấm cúng khi xa nhà của bạn, nơi bạn sẽ cảm thấy hạnh phúc khi trở về mỗi lần ghé thăm Hà Nội',348877),
		(5,'AuroraHomestayDalat',2,1,N'50 Sương Nguyệt Ánh, Phường 9, Đà Lạt, Tỉnh Lâm Đồng, Việt Nam','108.4548617','11.9513023','14:00','12:00',20,N'Aurora Homestay Dalat là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một nơi nghỉ vừa thoải mái lại hợp túi tiền.Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Aurora Homestay Dalat sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Aurora Homestay Dalat, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.Nếu dự định có một kỳ nghỉ dài, thì Aurora Homestay Dalat chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Aurora Homestay Dalat sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.Du lịch một mình cũng không hề kém phần thú vị và Aurora Homestay Dalat là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.','300600'),
		(5,'DalatLacasaHomestayIV',3,1,N'2B Hồ Tùng Mậu, Phường 3, Đà Lạt, Tỉnh Lâm Đồng, Việt Nam','108.4354','11.9394327','14:00','12:00',50,N'Dalat Lacasa Homestay IV là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một nơi nghỉ vừa thoải mái lại hợp túi tiền.Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Dalat Lacasa Homestay IV sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.Du lịch một mình cũng không hề kém phần thú vị và Dalat Lacasa Homestay IV là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.','309600'),
		(5,'DocMocHomestay',3,1,N'132A Hoang Hoa Tham, phường 10, Đà Lạt, Tỉnh Lâm Đồng, Việt Nam','108.4250277','11.9407603','14:00','12:00',60,N'Doc Moc Homestay là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một nơi nghỉ vừa thoải mái lại hợp túi tiền.Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Doc Moc Homestay sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Doc Moc Homestay , một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.Du lịch một mình cũng không hề kém phần thú vị và Doc Moc Homestay là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.Doc Moc Homestay là lựa chọn thông thái nhất cho những ai đang tìm kiếm một nơi nghỉ với dịch vụ xuất sắc nhưng hợp với túi tiền.','468600'),
		(5,'HomestaySweetValley',2,1,N'B17 Mạc Đĩnh Chi, Phường 4, Đà Lạt, Tỉnh Lâm Đồng, Việt Nam',' 108.4611908','11.9385157','14:00','12:00',60,N'Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Homestay Sweet Valley, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Homestay Sweet ValleyNếu dự định có một kỳ nghỉ dài, thì Homestay Sweet Valley chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Homestay Sweet Valley sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.','548600'),
		(5,'LengKengHomestayDalat',2,1,N'57/4 Hoàng Hoa Thám, phường 10, Đà Lạt, Tỉnh Lâm Đồng, Việt Nam',' 108.4623411','11.9362739','14:00','12:00',60,N'LengKeng Homestay Dalat là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một nơi nghỉ vừa thoải mái lại hợp túi tiền.Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, LengKeng Homestay Dalat sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.Khi lưu trú tại nơi nghỉ thì nội thất và kiến trúc hẳn là hai yếu tố quan trọng khiến quý khách mãn nhãn. Với thiết kế độc đáo, LengKeng Homestay Dalat mang đến không gian lưu trú làm hài lòng quý khách.','928600'),
		(5,'Zodiac House Dalat',3,1,N'27 Lê Văn Tám, phường 10, Đà Lạt, Tỉnh Lâm Đồng, Việt Nam','108.4549827','11.9386396','14:00','12:00',60,N'Zodiac House Dalat là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một nơi nghỉ vừa thoải mái lại hợp túi tiền.
Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Zodiac House Dalat sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Zodiac House Dalat, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân. Nếu dự định có một kỳ nghỉ dài, thì Zodiac House Dalat chính là lựa chọn dành cho quý khách. Với đầy đủ tiện nghi với chất lượng dịch vụ tuyệt vời, Zodiac House Dalat sẽ khiến quý khách cảm thấy thoải mái như đang ở nhà vậy.
Du lịch một mình cũng không hề kém phần thú vị và Zodiac House Dalat là nơi thích hợp dành riêng cho những ai đề cao sự riêng tư trong kỳ lưu trú.',350600),
		(5,'MayNgangDoiHomestay',3,1,N' 140 Bui Thi Xuan, Ward 2, Phường 2, Đà Lạt, Tỉnh Lâm Đồng, Việt Nam','108.4372957','11.9485323','14:00','12:00',35,N'Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại May Ngang Doi Homestay Da Lat, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.','229600'),
		(5,'RaccoonPawsHomestayDalat',3,1,N' 30/8 Kim Dong, Phường 6, Đà Lạt, Tỉnh Lâm Đồng, Việt Nam','108.4247774','11.9529721','14:00','12:00',35,N'Raccoon & Paws Homestay Dalat là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một nơi nghỉ vừa thoải mái lại hợp túi tiền.Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Raccoon & Paws Homestay Dalat sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Raccoon & Paws Homestay Dalat, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.','328600'),
		(3,'SixSensesNinhVanBay',5,1,N'Ninh Van Bay, Nha Trang, Ninh Hòa, Khánh Hòa, Việt Nam','109.2764009','12.3590312','14:00','12:00',100,N'Dịch vụ thượng hạng song hành với hàng loạt tiện nghi phong phú sẽ đem đến cho quý khách trải nghiệm của một kỳ nghỉ viên mãn nhất.Trung tâm thể dục của nơi nghỉ là một trong những tiện nghi không thể bỏ qua khi lưu trú tại đây.Hưởng thụ một ngày thư thái đầy thú vị tại hồ bơi dù quý khách đang du lịch một mình hay cùng người thân.','20601600')
		

		


select * from HINHANH
delete from HINHANH
DBCC CHECKIDENT ('HINHANH', RESEED, 0)
insert into HINHANH
select 1,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\CharmingHotel2.jpg',Single_Blob) as picture;
insert into HINHANH
select 1,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\CharmingHotel1.jpg',Single_Blob) as picture;
insert into HINHANH
select 2,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\82431934.jpg',Single_Blob) as picture;
insert into HINHANH
select 2,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\219467680.jpg',Single_Blob) as picture;
insert into HINHANH
select 2,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\83804376.jpg',Single_Blob) as picture;
insert into HINHANH
select 2,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\269522335.jpg',Single_Blob) as picture;
insert into HINHANH
select 2,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\81044353.jpg',Single_Blob) as picture;
insert into HINHANH
select 3,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\img1.jpg',Single_Blob) as picture;
insert into HINHANH
select 3,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\img2.jpg',Single_Blob) as picture;
insert into HINHANH
select 3,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\img3.jpg',Single_Blob) as picture;
insert into HINHANH
select 3,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\img4.jpg',Single_Blob) as picture;
insert into HINHANH
select 4,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\DPMBResort_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 4,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\DPMBResort_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 4,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\DPMBResort_dichvu_anh8.jpeg',Single_Blob) as picture;
insert into HINHANH
select 4,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\DPMBResort_dichvu_anh7.jpeg',Single_Blob) as picture;
insert into HINHANH
select 5,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\eden_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 5,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\eden_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 5,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\eden_dichvu_anh9.jpeg',Single_Blob) as picture;
insert into HINHANH
select 5,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\eden_anh5.jpeg',Single_Blob) as picture;
insert into HINHANH
select 5,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\eden_anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 6,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\namnghi_anh8.jpeg',Single_Blob) as picture;
insert into HINHANH
select 6,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\namnghi_anh7.jpeg',Single_Blob) as picture;
insert into HINHANH
select 6,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\namnghi_anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 6,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\namnghi_anh9.jpeg',Single_Blob) as picture;
insert into HINHANH
select 6,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\namnghi_anh10.jpeg',Single_Blob) as picture;
insert into HINHANH
select 7,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\VinOasis_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 7,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\VinOasis_anh6.jpeg',Single_Blob) as picture;
insert into HINHANH
select 7,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\VinOasis_dichvu_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 7,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\VinOasis_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 7,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\VinOasis_dichvu_anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 8,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\img26.jpg',Single_Blob) as picture;
insert into HINHANH
select 8,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\img36.jpg',Single_Blob) as picture;
insert into HINHANH
select 8,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\img16.jpg',Single_Blob) as picture;
insert into HINHANH
select 9,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiGatsbyHotel\img2.jpg',Single_Blob) as picture;
insert into HINHANH
select 9,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiGatsbyHotel\img1.jpg',Single_Blob) as picture;
insert into HINHANH
select 9,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\img9.jpg',Single_Blob) as picture;
insert into HINHANH
select 10,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\img17.jpg',Single_Blob) as picture;
insert into HINHANH
select 10,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\img27.jpg',Single_Blob) as picture;
insert into HINHANH
select 10,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\img47.jpg',Single_Blob) as picture;
insert into HINHANH
select 10,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\img37.jpg',Single_Blob) as picture;
insert into HINHANH
select 11,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\img15.jpg',Single_Blob) as picture;
insert into HINHANH
select 11,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\img25.jpg',Single_Blob) as picture;
insert into HINHANH
select 11,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\imh35.jpg',Single_Blob) as picture;
insert into HINHANH
select 12,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\LAzure_dichvu_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 12,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\LAzure_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 12,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\LAzure_anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 12,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\LAzure_anh5.jpeg',Single_Blob) as picture;
insert into HINHANH
select 12,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\LAzure_dichvu_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 13,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\img24.jpg',Single_Blob) as picture;
insert into HINHANH
select 13,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\img34.jpg',Single_Blob) as picture;
insert into HINHANH
select 13,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\img14.jpg',Single_Blob) as picture;
insert into HINHANH
select 14,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\img2.jpg',Single_Blob) as picture;
insert into HINHANH
select 14,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\img3.jpg',Single_Blob) as picture;
insert into HINHANH
select 14,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\img4.jpg',Single_Blob) as picture;
insert into HINHANH
select 15,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\86346794.jpg',Single_Blob) as picture;
insert into HINHANH
select 15,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\86346754.jpg',Single_Blob) as picture;
insert into HINHANH
select 15,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\95412305.jpg',Single_Blob) as picture;
insert into HINHANH
select 15,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\95412338.jpg',Single_Blob) as picture;
insert into HINHANH
select 15,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\184992880.jpg',Single_Blob) as picture;
insert into HINHANH
select 16,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\201220879.jpg',Single_Blob) as picture;
insert into HINHANH
select 16,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\206809501.jpg',Single_Blob) as picture;
insert into HINHANH
select 16,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\258257701.jpg',Single_Blob) as picture;
insert into HINHANH
select 16,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\264686984.jpg',Single_Blob) as picture;
insert into HINHANH
select 16,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\258257703.jpg',Single_Blob) as picture;
insert into HINHANH
select 17,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\197003421.jpg',Single_Blob) as picture;
insert into HINHANH
select 17,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\193813423.jpg',Single_Blob) as picture;
insert into HINHANH
select 17,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\193813425.jpg',Single_Blob) as picture;
insert into HINHANH
select 17,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\257023614.jpg',Single_Blob) as picture;
insert into HINHANH
select 18,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\img7.jpg',Single_Blob) as picture;
insert into HINHANH
select 18,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\img9.jpg',Single_Blob) as picture;
insert into HINHANH
select 18,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\img6.jpg',Single_Blob) as picture;
insert into HINHANH
select 18,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\img2.jpg',Single_Blob) as picture;
insert into HINHANH
select 18,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\img1.jpg',Single_Blob) as picture;
insert into HINHANH
select 19,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\img1.jpg',Single_Blob) as picture;
insert into HINHANH
select 19,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\img2.jpg',Single_Blob) as picture;
insert into HINHANH
select 19,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\img3.jpg',Single_Blob) as picture;
insert into HINHANH
select 19,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\img4.jpg',Single_Blob) as picture;
insert into HINHANH
select 19,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\img5.jpg',Single_Blob) as picture;
insert into HINHANH
select 20,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\anh1.jpg',Single_Blob) as picture;
insert into HINHANH
select 20,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\anh2.jpg',Single_Blob) as picture;
insert into HINHANH
select 20,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\anh3.jpg',Single_Blob) as picture;
insert into HINHANH
select 20,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\anh4.jpg',Single_Blob) as picture;
insert into HINHANH
select 20,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\anh5.jpg',Single_Blob) as picture;
insert into HINHANH
select 20,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\anh6.jpg',Single_Blob) as picture;
insert into HINHANH
select 21,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Pine Forest Villas\anh1.jpg',Single_Blob) as picture;
insert into HINHANH
select 21,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Pine Forest Villas\anh2.jpg',Single_Blob) as picture;
insert into HINHANH
select 21,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Pine Forest Villas\anh3.jpg',Single_Blob) as picture;
insert into HINHANH
select 21,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Pine Forest Villas\anh4.jpg',Single_Blob) as picture;
insert into HINHANH
select 21,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Pine Forest Villas\anh5.jpg',Single_Blob) as picture;
insert into HINHANH
select 21,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Pine Forest Villas\anh6.jpg',Single_Blob) as picture;
insert into HINHANH
select 22,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\anh1.jpg',Single_Blob) as picture;
insert into HINHANH
select 22,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\anh2.jpg',Single_Blob) as picture;
insert into HINHANH
select 22,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\anh3.jpg',Single_Blob) as picture;
insert into HINHANH
select 22,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\anh4.jpg',Single_Blob) as picture;
insert into HINHANH
select 22,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\anh5.jpg',Single_Blob) as picture;
insert into HINHANH
select 23,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\MovenpickResortCamRanh_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 23,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\MovenpickResortCamRanh_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 23,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\MovenpickResortCamRanh_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 24,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\AmianaResortNhaTrang_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 24,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\AmianaResortNhaTrang_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 24,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\AmianaResortNhaTrang_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 24,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\AmianaResortNhaTrang_anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 25,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\VinpearlPQResort_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 25,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\VinpearlPQResort_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 25,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\VinpearlPQResort_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 25,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\VinpearlPQResort_anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 25,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\VinpearlPQResort_anh5.jpeg',Single_Blob) as picture;
insert into HINHANH
select 26,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\RadissonBlu_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 26,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\RadissonBlu_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 26,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\RadissonBlu_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 27,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\anh1.jpg',Single_Blob) as picture;
insert into HINHANH
select 27,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\anh2.jpg',Single_Blob) as picture;
insert into HINHANH
select 27,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\anh3.jpg',Single_Blob) as picture;
insert into HINHANH
select 27,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\anh4.jpg',Single_Blob) as picture;
insert into HINHANH
select 27,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\anh5.jpg',Single_Blob) as picture;
insert into HINHANH
select 27,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\anh6.jpg',Single_Blob) as picture;
insert into HINHANH
select 28,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 28,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 28,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 28,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 28,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\anh5.jpeg',Single_Blob) as picture;
insert into HINHANH
select 28,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\anh6.jpeg',Single_Blob) as picture;
insert into HINHANH
select 29,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\anh1.jpg',Single_Blob) as picture;
insert into HINHANH
select 29,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\anh2.jpg',Single_Blob) as picture;
insert into HINHANH
select 29,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\anh3.jpg',Single_Blob) as picture;
insert into HINHANH
select 29,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\anh4.jpg',Single_Blob) as picture;
insert into HINHANH
select 29,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\anh5.jpg',Single_Blob) as picture;
insert into HINHANH
select 29,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\anh6.jpg',Single_Blob) as picture;
insert into HINHANH
select 30,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\anh1.jpg',Single_Blob) as picture;
insert into HINHANH
select 30,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\anh2.jpg',Single_Blob) as picture;
insert into HINHANH
select 30,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\anh3.jpg',Single_Blob) as picture;
insert into HINHANH
select 30,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\anh4.jpg',Single_Blob) as picture;
insert into HINHANH
select 30,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\anh5.jpg',Single_Blob) as picture;
insert into HINHANH
select 30,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\anh6.jpg',Single_Blob) as picture;

insert into HINHANH
select 31,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\AuroraHomestayDalat_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 31,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\AuroraHomestayDalat_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 31,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\AuroraHomestayDalat_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 31,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\AuroraHomestayDalat_anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 31,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\AuroraHomestayDalat_anh5.jpeg',Single_Blob) as picture;
insert into HINHANH
select 32,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DalatLacasaHomestayIV\DalatLacasaHomestayIV_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 32,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DalatLacasaHomestayIV\DalatLacasaHomestayIV_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 32,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DalatLacasaHomestayIV\DalatLacasaHomestayIV_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 33,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\DocMocHomestay_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 33,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\DocMocHomestay_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 33,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\DocMocHomestay_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 33,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\DocMocHomestay_anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 34,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\HomestaySweetValley_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 34,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\HomestaySweetValley_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 34,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\HomestaySweetValley_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 34,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\HomestaySweetValley_anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 35,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\LengKengHomestayDalat_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 35,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\LengKengHomestayDalat_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 35,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\LengKengHomestayDalat_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 35,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\LengKengHomestayDalat_anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 35,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\LengKengHomestayDalat_anh5.jpeg',Single_Blob) as picture;
insert into HINHANH
select 35,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\LengKengHomestayDalat_anh6.jpeg',Single_Blob) as picture;
insert into HINHANH
select 35,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\LengKengHomestayDalat_anh7.jpeg',Single_Blob) as picture;
insert into HINHANH
select 35,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\LengKengHomestayDalat_anh8.jpeg',Single_Blob) as picture;
insert into HINHANH
select 35,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\LengKengHomestayDalat_anh9.jpeg',Single_Blob) as picture;
insert into HINHANH
select 36,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\ZodiacHouseDalat_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 36,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\ZodiacHouseDalat_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 36,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\ZodiacHouseDalat_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 36,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\ZodiacHouseDalat_anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 37,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MayNgangDoiHomestay\MayNgangDoiHomestay_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 37,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MayNgangDoiHomestay\MayNgangDoiHomestay_anh2.jpeg',Single_Blob) as picture;

insert into HINHANH
select 38,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RaccoonPawsHomestayDalat\RaccoonPawsHomestayDalat_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 38,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RaccoonPawsHomestayDalat\RaccoonPawsHomestayDalat_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 38,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RaccoonPawsHomestayDalat\RaccoonPawsHomestayDalat_anh3.jpeg',Single_Blob) as picture;

insert into HINHANH
select 39,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\SixSensesNinhVanBay_anh1.jpeg',Single_Blob) as picture;
insert into HINHANH
select 39,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\SixSensesNinhVanBay_anh2.jpeg',Single_Blob) as picture;
insert into HINHANH
select 39,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\SixSensesNinhVanBay_anh3.jpeg',Single_Blob) as picture;
insert into HINHANH
select 39,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\SixSensesNinhVanBay_anh4.jpeg',Single_Blob) as picture;
insert into HINHANH
select 39,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\SixSensesNinhVanBay_anh5.jpeg',Single_Blob) as picture;




select * from TIENNGHIDV
delete from TIENNGHIDV
DBCC CHECKIDENT ('TIENNGHIDV', RESEED, 0)
insert into TIENNGHIDV
values	(1,N'Phục vụ ăn uống tại phòng',null),
		(1,N'Phục vụ bữa sáng miễn phí',null),
		(1,N'Dành cho người khuyết tật',N'Đường cho xe lăn, Thiết bị cho người khiếm thính'),
		(2,N'Sân tennis',null),
		(2,N'Bữa sáng miễn phí',null),
		(2,N'ATM và dịch vụ thu đổi ngoại tệ',null),
		(2,N'Xe đưa đón miễn phí',null),
		(3,N'Bữa sáng tự chọn miễn phí',null),
		(3,N'Dịch vụ đưa đón sân bay', null),
		(4,N'Đưa đón miễn phí',null),
		(4,N'CLB trẻ em và dịch vụ thu đổi ngoại tệ cho khách',null),
		(4,N'Dịch vụ đưa đón sân bay', null),
		(4,N'Hỗ trợ người khuyết tật',N'Đường cho xe lăn, Thiết bị phù hợp'),
		(5,N'Cà phê/trà tại sảnh', null),
		(5,N'Lễ tân 24/24', null),
		(5,N'Đưa đón, bữa sáng miễn phí', null),
		(6,N'Bữa sáng miễn phí', null),
		(6,N'Hỗ trợ người khuyết tật',null),
		(6,N'Đưa đón sân bay, khu vực lân cận', null),
		(7,N'Phục vụ ăn uống tại phòng',null),
		(7,N'ATM và dịch vụ thu đổi ngoại tệ',null),
		(7,N'Lễ tân, bảo vệ 24/24h',null),
		(7,N'Hỗ trợ người khuyết tật', null),
		(8,N'Bữa sáng miễn phí',null),
		(9,N'Lễ tân 24h',null),
		(9,N'Dịch vụ thu đổi ngoại tệ',null),
		(10,N'Bữa sáng miễn phí',null),
		(10,N'Đưa đón khu vực lân cận',null),
		(11,N'Có trang bị tủ lạnh mỗi phòng', null),
		(11,N'Phục vụ ăn uống tại phòng',null),
		(12,N'Đưa đón sân bay, khu vực lân cận',null),
		(12,N'Phục vụ ăn uống tại phòng',null),
		(12,N'ATM và dịch vụ thu đổi ngoại tệ',null),
		(12,N'Lễ tân và bảo vệ 24/24h',null),
		(13,N'Dịch vụ thu đổi ngoại tệ',null),
		(13,N'Có xe đưa đón sân bay',null),
		(14,N'Bữa sáng miễn phí', null),
		(15,N'ATM và dịch vụ Thu Đổi Ngoại Tệ', null),
		(15,N'Sân tennis, Hồ câu cá', null),
		(16,N'ATM và dịch vụ Thu Đổi Ngoại Tệ', null),
		(16,N'Sân tennis, Hồ câu cá', null),
		(16,N'Hỗ trợ người khuyết tật',null),
		(17,N'Hỗ trợ người khuyết tật',null),
		(17,N'Đưa đón sân bay', null),
		(17,N'ATM và dịch vụ Thu Đổi Ngoại Tệ', null),
		(18,N'ATM và dịch vụ Thu Đổi Ngoại Tệ', null),
		(18,N'Bữa sáng miễn phí', null),
		(18,N'Hỗ trợ người khuyết tật',null),
		(19,N'Hỗ trợ người khuyết tật',null),
		(19,N'ATM và dịch vụ Thu Đổi Ngoại Tệ', null),
		(19,N'Phục vụ ăn uống tại phòng', null),
		(20,N'Phục vụ ăn uống', null),
		(20,N'Quang cảnh đẹp',null),
		(20,N'Cafe trà tại sảnh',null),
		(21,N'Quang cảnh đẹp',null),
		(21,N'Khu vực ngoài trời mở tiệc BBQ',null),
		(21,N'Hái trái cây tại vườn',null),
		(22,N'Vị trí đồi cao view đẹp', null),
		(22,N'Thác nước nhân tạo, ao câu cá, có con đường quanh núi dạo chơi, ngắm cảnh', null),
		(22,N'Khu vực nướng BBQ và bàn ăn ngoài trời', null),
		(23,N'Công viên nước',null),
		(23,N'Karaoke, Sòng Bài',null),
		(23,N'dịch vụ thu đổi ngoại tệ',null),
		(23,N'Hỗ trợ người khuyết tật', null),
		(23,N'Dù ô bãi biễn', null),
		(24,N'Công viên nước',null),
		(24,N'Dịch vụ thu đổi ngoại tệ 24/24',null),
		(24,N'Hỗ trợ người khuyết tật', null),
		(24,N'Các trò chơi trên biển',null),
		(25,N'Phục vụ ăn uống tại phòng',null),
		(25,N'Trò chơi bãi biển',null),
		(25,N'Dịch vụ thu đổi ngoại tệ',null),
		(25,N'bảo vệ, lễ tân 24/24', null),
		(25,N'Hỗ trợ người khuyết tật',null),
		(26,N'Dịch vụ đưa đón sân bay',null),
		(26,N'Dịch vụ thu đổi ngoại tệ 24/24',null),
		(26,N'Trò chơi bãi biển, karaoke',null),
		(26,N'Hỗ trợ người khuyết tật', null),
		(27,N'Lễ tân, bảo vệ 24/24h',null),
		(27,N'Cà phê/trà tại sảnh',null),
		(27,N'Hỗ trợ người khuyết tật',null),
		(28,N'Cà phê/trà tại sảnh',null),
		(28,N'Dịch vụ phòng, lễ tân 24h',null),
		(28,N'Đưa đón khu vực lân cận',null),
		(29,N'Nhân viên xách hành lý',null),
		(29,N'Lễ tân, bảo vê 24h',null),
		(29,N'Cà phê/trà tại sảnh',null),
		(29,N'Đưa đón sân bay', null),
		(29,N'Hỗ trợ người khuyết tật',null),
		(30,N'Lê tân 24h', null),
		(30,N'Đưa đón trong khu vực', null),
		(31,N'Phực vu ăn uống tại phòng',null),
		(31,N'thích thì đi đâu cũng được',null),
		(32,N'Phục vụ bữa sáng miễn phí ',null),
		(32,N'Dịch vụ đưa đón sân bay',null),
		(33,N'Phục vụ ăn uống miễn phí',null),
		(33,N'Đưa đón miễn phí ',null),
		(34,N'Đưa đón miễn phí ',null),
		(34,N'Bữa sáng miễn phí ',null),
		(35,N'Đưa đón miễn phí ',null),
		(35,N'Phục vụ ăn uống tại phòng',null),
		(36,N'Lễ tân, bảo vệ 24h', null),
		(36,N'đưa đón khu vực lân cận',null),
		(36,N'Phục vụ ăn uống tại phòng',null),
		(37,N'ATM và dịch vụ Thu Đổi Ngoại Tệ',null),
		(37,N'Lễ tân và bảo vệ 24/24h',null),

		(38,N'Bữa sáng miễn phí',null),
		(38,N'Dịch vụ đưa đón sân bay',null),

		(39,N'Bữa sáng tự chọn miễn phí',null),
		(39,N'Dịch vụ đưa đón sân bay', null),
		(39,N'Hỗ trợ người khuyết tật',null),
		(39,N'ATM và dịch vụ thu đổi ngoại tệ',null)


select * from TIENNGHIHD
delete from TIENNGHIHD
DBCC CHECKIDENT ('TIENNGHIHD', RESEED, 0)
insert into TIENNGHIHD
values	(1,1,1,0,0,0,0,1,1,0,0),
		(2,1,1,1,1,1,1,1,1,1,1),
		(3,1,1,0,0,1,0,1,1,0,1),
		(4,1,1,1,1,1,0,1,1,1,1),
		(5,1,1,1,1,1,1,1,1,1,1),
		(6,1,1,1,1,1,1,1,1,1,1),
		(7,1,1,1,1,1,1,1,1,1,1),
		(8,1,1,0,0,1,0,1,1,0,1),
		(9,1,1,0,0,0,0,1,1,0,0),
		(10,1,1,0,0,1,1,1,1,0,0),
		(11,1,1,0,0,1,0,1,1,1,0),
		(12,1,1,1,1,1,0,1,1,1,1),
		(13,1,1,1,1,1,1,1,1,1,1),
		(14,1,1,0,0,1,1,1,1,1,0),
		(15,1,1,1,1,1,0,1,1,1,1),
		(16,1,1,0,1,1,1,1,1,1,0),
		(17,1,1,0,1,1,1,1,1,1,1),
		(18,1,1,1,1,1,1,1,1,1,1),
		(19,1,1,1,1,1,1,1,1,1,0),
		(20,1,1,1,1,0,1,1,0,0,0),
		(21,1,1,1,1,0,1,1,0,0,0),
		(22,1,1,1,1,0,1,1,0,0,0),
		(23,1,1,1,1,1,1,1,1,1,1),
		(24,1,1,1,1,1,0,1,1,1,1),
		(25,1,1,1,1,1,0,1,1,1,1),
		(26,1,1,1,1,1,1,1,1,1,1),
		(27,1,1,0,1,1,1,1,0,0,0),
		(28,1,1,0,1,0,1,1,0,0,0),
		(29,1,1,0,1,1,1,1,1,1,0),
		(30,1,1,0,1,0,0,1,1,1,0),
		(31,1,1,1,0,1,0,1,1,0,0),
		(32,1,1,1,0,1,0,1,1,0,0),
		(33,1,1,1,1,1,0,1,1,0,1),
		(34,1,1,1,0,0,1,1,1,1,1),
		(35,1,1,1,0,1,1,1,1,1,1),
		(36,1,1,0,1,0,1,1,0,0,0),
		(37,1,1,0,0,1,0,1,1,1,0),
		(38,1,1,0,1,1,1,1,1,1,0),
		(39,1,1,1,1,1,1,1,1,1,1)


select * from PHONG
delete from PHONG
DBCC CHECKIDENT ('PHONG',RESEED,0)
insert into PHONG
values	(1,'Day Use Room',5,15,150000,1,2,1,N'3 Hours Use Only - Phòng Chỉ Ở 3 Giờ Trong Ngày - Không Ở Qua Đêm'),
		(1,'Standard Double',5,15,225000,1,2,1,null),
		(1,'Triple Room',2,18,250000,2,2,2,null),
		(1,'Executive Twin',5,15,350000,1,1,1,null),
		(1,'Deluxe Twin',3,17,420000,1,2,1,null),
		(2,'Junior Suite King',10,40,1856000,2,3,2,null),
		(2,'Thera Suite Room',8,45,2506000,2,3,2,null),
		(2,'Duplex Residence',3,120,8545000,2,3,3,null),
		(3,'Classic Standard',10,25,745600,1,2,1,null),
		(3,'Twin Room',10,20,1007000,1,2,1,null),
		(3,'Executive Suite',10,35,2226000,2,3,2,null),
		(3,'Studio Superior',10,30,3117250,1,1,0,null),
		(4,'Junior Suite',2,60,1958000,2,2,1,null),
		(4,'Deluxe Ocean King',2,50,1990000,1,2,1,null),
		(4,'Deluxe Ocean Twin',2,85,2875000,2,2,2,null),
		(4,'Executive Suite',2,64,2955246,1,2,0,null),
		(5,'Grand Deluxe Ocean',35,46,2493000,2,2,1,null),
		(5,'Deluxe Ocean',40,33,2216345,1,2,1,null),
		(5,'Bungalow Beach Front',10,85,4515000,2,2,2,null),
		(6,'Deluxe Twin Bed',18,31,4040000,2,2,2,null),
		(6,'Ocean Deluxe',20,45,5452620,2,2,1,null),
		(6,'Blue Ocean Suite',7,93,8745000,2,2,1,null),
		(7,'Standard King',450,42,1601600,1,2,1,null),
		(7,'StandardTwin',500,42,2288000,2,2,1,null),
		(7,'JuniorSuite',350,84,3203200,1,2,1,null),
		(8,'Deluxe View',15,35,1850200,1,1,1,null),
		(8,'Triple',15,45,2540306,2,2,1,null),
		(8,'Family View',7,50,3500000,2,2,2,null),
		(9,'Deluxe',15,25,499000,1,2,0,null),
		(9,'Executive',15,28,659500,1,2,0,null),
		(9,'Family Suite',5,45,1450210,2,2,1,null),
		(10,'Deluxe',18,30,613158,2,2,1,null),
		(10,'Luxury',17,25,1195213,1,2,0,null),
		(10,'Luxury City',10,30,1888734,1,2,1,null),
		(10,'Studio Suite',10,30,2150000,1,2,0,null),
		(11,'Executive',13,28,672794,1,2,0,null),
		(11,'Junior Suite',10,38,1309201,1,2,1,null),
		(11,'Imperial Suite',10,42,1662457,1,2,1,null),
		(11,'Family Suite',10,42,1893452,2,2,2,null),
		(12,'Deluxe Garden',15,31,3501000,1,2,0,null),
		(12,'Deluxe Sea',20,31,4041000,2,2,0,null),
		(12,'Suite Executive',20,58,5841000,1,2,1,null),
		(13,'Premium Queen',100,41,4526000,1,2,1,null),
		(13,'Premium Twin',100,35,2886500,2,2,0,null),
		(13,'Suite Club',82,45,11582000,1,2,0,null),
		(13,'Grand Twin',82,45,7821000,1,2,1,null),
		(14,'Classic Double',15,22,777000,1,2,0,null),
		(14,'Junior Suite',20,32,985000,1,2,0,null),
		(14,'Family',10,40,1801000,2,2,2,null),
		(14,'Studio Double',10,30,3166000,1,2,1,null),
		(15,N'Căn hộ 1 phòng ngủ',30,70,2337000,1,2,1,null),
		(15,N'Căn hộ 2 phòng ngủ',30,110,3034000,2,2,2,null),
		(16,N'Superior',80,25,1719023,1,2,0,null),
		(16,N'Superior Twin',80,31,2022380,2,2,1,null),
		(16,N'Deluxe King Bed',100,45,2303266,1,1,1,null),
		(16,N'Deluxe Twin Bed',63,45,2584153,2,2,1,null),
		(17,N'Superior',50,28,931600,1,2,1,null),
		(17,N'Premier Deluxe',80,45,1323000,1,2,1,null),
		(17,N'Grand Suite',71,96,2268000,1,2,1,null),
		(18,N'Queen Resort Classic Ocean View',45,45,7636879,1,2,0,null),
		(18,N'King Classic Terrace Suite',45,65,10182500,2,2,1,null),
		(18,N'King Son Tra Terrace Suite',45,55,11455432,1,2,0,null),
		(18,N'Bedroom Heavenly Penthouse',45,96,22910637,1,2,1,null),
		(19,N'Bliss Suite',55,65,1147500,1,2,0,null),
		(19,N'Peace Suite',55,58,1147500,1,2,0,null),
		(19,N'Harmony Suite',45,76,1586250,1,2,1,null),
		(20,N'Nguyên Căn Full House',1,35,1200000,1,2,1,null),
		(20,N'Nguyên Căn Happy House',2,30,1200000,1,2,1,null),
		(20,N'Nguyên căn The Rooftop',1,35,1500000,1,2,1,null),
		(20,N'Nguyên căn Nhà Kính',1,65,4500000,3,6,4,null),
		(21,N'Nguyên căn 2 phòng ngủ',4,65,4500000,2,4,2,null),
		(22,N'Nguyên căn HillTop',2,80,5000000,8,15,8,null),
		(23,'Superior 1 King Bed Sea View',20,40,1489111,1,2,0,null),
		(23,'Deluxe 1 King Bed Sea View',20,40,2391000,1,2,1,null),
		(23,'Villa Bedroom Private Pool Sea View',10,50,5394848,1,2,1,null),
		(24,'Deluxe King',10,65,1852000,1,2,0,null),
		(24,'Deluxe Ocean',10,70,2214000,1,2,1,null),
		(24,'Ocean Villa',10,80,2586721,1,2,1,null),
		(24,'cean Villa Family',10,120,4066508,3,4,3,null),
		(25,'Deluxe Twin',20,42,2001600,1,2,1,null),
		(25,'Deluxe King',20,42,2901000,1,2,1,null),
		(25,'One Executive Suite',20,200,7788000,2,4,2,null),
		(26,'Deluxe King View',20,45,2601600,1,2,1,null),
		(26,'One Bedroom Suite',20,65,3788000,2,2,2,null),
		(26,'ExecuiveSuite',20,80,4503200,2,2,2,null),
		(27,'Standard 1 Queen Bed',15,42,1146393,1,2,0,null),
		(27,'Standard 2 Twin Beds',15,60,1607000,2,4,2,null),
		(27,'Superior 1 Queen Bed',15,45,1480000,1,2,1,null),
		(27,'Deluxe 1 Queen Bed With Sofa Bed',15,50,2532000,1,2,1,null),
		(28,'Superior',15,18,212750,1,2,0,null),
		(28,'Deluxe',15,18,309000,1,2,0,null),
		(28,'Suite Balcony',15,25,518925,1,2,1,null),
		(28,'Family Junior Suite',15,35,676820,2,2,2,null),
		(29,'Superior 1 King Bed',20,25,1624000,1,2,0,null),
		(29,'Superior Twin 2 Beds',20,35,2450000,2,2,2,null),
		(29,'Deluxe 1 King Bed',20,25,2140000,1,2,1,null),
		(30,'Deluxe Double With Window',20,19,348877,1,2,0,null),
		(30,'Deluxe Double Or Twin',20,23,387641,1,2,0,null),
		(30,'Deluxe Honeymoon',20,21,581461,1,2,1,null),
		(30,'Family City View',20,45,852809,2,4,2,null),
		(31,N'Double Room',10,11,300600,1,2,1,null),
		(31,N'Family Room',10,20,450000,2,2,2,null),
		(32,N'Arcane one',30,20,450000,1,2,1,null),
		(32,N'Arcane Double',20,30,500600,2,2,2,null),
		(33,N'Economy Double',20,30,468600,1,2,1,null),
		(33,N'Standard Double',20,30,750600,2,2,2,null),
		(33,N'Family',20,30,890600,2,2,2,null),
		(34,N'Sweet Valley 1 Double Bed',15,40,548600,1,2,1,null),
		(34,N'Sweet Valley 2 Double Beds',25,20,803600,2,2,2,null),
		(35,N'Sparking',16,35,928600,2,2,2,null),
		(35,N'Special',20,25,1503600,2,2,2,null),
		(36,'Basic Hill View',20,20,350600,1,2,0,null),
		(36,'Basic Double Hill View',20,35,630600,2,4,2,null),
		(36,'Family Hill View',20,45,1130600,4,8,4,null),
		(37,N'Double With Window ',20,16,229600,1,2,0,null),
		(37,N'Family With Balcony ',15,20,449600,1,2,1,null),
		(38,N'Standard Double',20,16,328600,1,2,1,null),
		(38,N'Triple Double ',20,16,1103600,2,2,2,null),
		(39,N'Hill Top Pool Villa',50,100,20601600,1,2,1,null),
		(39,N'Pool Villa Beachfront ',25,100,23788000,2,2,2,null),
		(39,N'The Rock Retreat',25,84,89203200,2,2,2,null)
		

select * from TIENNGHIPHONG
delete from TIENNGHIPHONG
DBCC CHECKIDENT ('TIENNGHIPHONG',RESEED,0)
insert into TIENNGHIPHONG
values	(1,1,1,0,1,0,1,1,1,0,1,1,1,0),
		(2,1,1,0,0,0,1,1,1,0,1,0,1,0),
		(3,1,0,0,1,0,1,1,1,0,1,0,1,0),
		(4,1,1,0,1,0,1,1,1,0,1,1,1,0),
		(5,1,1,0,1,0,1,1,1,0,1,0,1,0),
		(6,1,1,1,1,1,1,1,1,1,1,1,1,0),
		(7,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(8,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(9,1,1,1,1,0,1,1,1,0,1,0,1,0),
		(10,1,1,1,1,0,1,1,1,0,0,0,1,0),
		(11,1,1,1,1,0,1,1,1,0,1,1,1,1),
		(12,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(13,1,1,1,1,0,1,1,1,1,1,1,1,0),
		(14,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(15,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(16,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(17,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(18,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(19,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(20,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(21,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(22,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(23,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(24,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(25,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(26,1,1,1,1,1,1,1,1,1,1,1,1,0),
		(27,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(28,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(29,1,0,0,0,0,1,1,1,0,1,0,1,0),
		(30,1,1,1,0,0,1,1,1,0,1,1,1,0),
		(31,1,1,0,1,1,1,1,0,1,1,1,1,0),
		(32,1,1,0,0,0,1,1,1,0,1,0,1,0),
		(33,1,1,1,1,0,1,1,1,0,1,1,1,0),
		(34,1,1,1,1,0,1,1,1,1,1,0,1,1),
		(35,1,1,1,1,0,1,1,1,1,1,0,1,1),
		(36,1,1,1,0,0,1,1,1,0,1,0,1,0),
		(37,1,1,1,1,0,1,1,1,0,1,1,1,1),
		(38,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(39,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(40,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(41,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(42,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(43,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(44,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(45,1,1,1,1,0,1,1,1,0,1,1,1,1),
		(46,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(47,1,1,1,1,0,1,1,1,0,1,1,1,0),
		(48,1,1,1,1,0,1,1,1,0,1,1,1,0),
		(49,1,1,1,1,0,1,1,1,0,1,1,1,0),
		(50,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(51,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(52,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(53,1,1,1,1,0,1,1,1,0,1,1,1,0),
		(54,1,1,1,1,0,1,1,1,1,1,1,1,0),
		(55,1,1,1,1,0,1,1,1,1,1,1,1,0),
		(56,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(57,1,1,1,1,0,1,1,1,0,1,1,1,0),
		(58,1,1,1,1,0,1,1,1,0,1,1,1,0),
		(59,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(60,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(61,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(62,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(63,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(64,1,1,1,1,0,1,1,1,0,1,1,1,0),
		(65,1,1,1,1,0,1,1,1,0,1,1,1,0),
		(66,1,1,1,1,0,1,1,1,1,1,1,1,0),
		(67,1,0,0,0,0,1,1,1,0,1,0,0,0),
		(68,1,0,0,0,0,1,1,1,0,1,1,1,0),
		(69,1,0,0,0,0,1,1,1,0,1,1,1,0),
		(70,1,1,0,1,1,1,1,0,1,1,1,0,0),
		(71,1,1,0,1,1,1,1,0,0,1,0,0,0),
		(72,1,1,1,1,1,1,1,0,1,1,1,0,1),
		(73,1,0,0,0,0,1,1,1,0,1,0,1,1),
		(74,1,0,1,0,0,1,1,1,0,1,1,0,1),
		(75,1,0,1,0,0,1,1,1,0,1,1,1,1),
		(76,1,0,1,1,0,1,1,1,1,1,1,0,1),
		(77,1,0,1,1,0,1,1,1,1,1,1,1,1),
		(78,1,0,1,1,0,1,1,1,1,1,1,1,1),
		(79,1,1,1,1,1,1,1,0,1,1,1,1,1),
		(80,1,0,1,0,0,1,1,1,0,1,1,1,1),
		(81,1,0,1,0,0,1,1,1,0,1,1,1,1),
		(82,1,1,1,1,1,1,1,1,1,1,1,0,1),
		(83,1,0,1,0,0,1,1,1,0,1,1,1,1),
		(84,1,1,1,1,0,1,1,1,0,1,1,1,1),
		(85,1,1,1,1,0,1,1,1,1,1,1,1,1),
		(86,1,0,1,0,0,1,1,1,0,1,0,1,0),
		(87,1,0,1,0,0,1,1,1,0,1,1,1,0),
		(88,1,1,1,1,0,1,1,1,0,1,1,0,0),
		(89,1,1,1,1,0,1,1,1,0,1,1,1,0),
		(90,1,0,1,0,0,1,1,1,0,1,1,1,0),
		(91,1,0,1,0,0,1,1,1,0,1,1,1,0),
		(92,1,0,1,0,0,1,1,1,0,1,1,1,0),
		(93,1,1,1,1,0,1,1,1,0,1,1,1,0),
		(94,1,0,1,0,0,1,1,1,0,1,1,0,0),
		(95,1,0,1,0,0,1,1,1,0,1,1,0,0),
		(96,1,0,1,0,0,1,1,1,0,1,1,1,0),
		(97,1,0,1,0,0,1,1,1,0,1,1,0,0),
		(98,1,0,1,0,0,1,1,1,0,1,1,1,0),
		(99,1,1,1,0,0,1,1,1,1,1,1,1,0),
		(100,1,1,1,1,0,1,1,1,1,1,1,1,0),
		(101,1,1,0,0,0,1,1,1,0,0,0,0,0),
		(102,1,1,0,0,0,1,1,1,0,0,0,0,0),
		(103,1,0,0,0,0,1,1,1,0,0,0,1,0),
		(104,1,1,0,0,0,1,1,1,0,0,0,1,1),
		(105,1,1,0,0,0,1,1,1,0,0,0,1,1),
		(106,1,1,0,1,1,1,0,0,1,0,1,1,1),
		(107,1,1,0,1,1,1,0,0,1,1,1,1,1),
		(108,1,1,0,0,0,1,1,1,1,0,1,1,1),
		(109,1,1,0,1,0,1,1,1,1,0,1,1,1),
		(110,1,1,0,1,1,0,1,1,1,1,0,1,1),
		(111,1,1,0,1,1,0,1,1,1,1,0,1,1),
		(112,1,0,1,0,0,1,1,1,1,0,1,1,0),
		(113,1,0,1,0,0,1,1,1,1,0,1,1,0),
		(114,1,1,1,1,0,1,1,1,0,1,1,0,0),

		(115,1,1,0,0,0,1,1,1,0,1,0,1,0),
		(116,1,1,0,1,1,0,1,1,1,1,0,1,1),
		(117,1,1,0,1,0,1,1,1,0,1,1,1,0),
		(118,1,1,1,1,0,1,1,1,1,1,1,1,0),
		(119,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(120,1,1,1,1,1,1,1,1,1,1,1,1,1),
		(121,1,1,1,1,1,1,1,1,1,1,1,1,1)
		



select * from HINHANHPHONG
delete from HINHANHPHONG
DBCC CHECKIDENT ('HINHANHPHONG',RESEED,0)
insert into HINHANHPHONG
select 1,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\BinhDan\CharmingHotel_binhdan12.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 1,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\BinhDan\CharmingHotel_binhdan42.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 1,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\BinhDan\binhdan32.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 2,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\BinhDan\binhdan221.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 2,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\BinhDan\binhdan222.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 2,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\BinhDan\binhdan223.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 3,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\BinhDan\CharmingHotel_binhdan22.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 3,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\BinhDan\CharmingHotel_binhdan32.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 3,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\BinhDan\CharmingHotel_binhdan52.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 4,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\CaoCap\CharmingHotel_caocap12.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 4,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\CaoCap\CharmingHotel_caocap42.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 4,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\CaoCap\CharmingHotel_caocap52.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 5,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\CaoCap\CharmingHotel_caocap22.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 5,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\CaoCap\CharmingHotel_caocap32.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 5,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\CaoCap\CharmingHotel_caocap52.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 6,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\phong1\214860421.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 6,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\phong1\269523678.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 6,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\phong2\213246941.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 7,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\phong1\269521833.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 7,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\phong2\269523670.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 7,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\phong2\214860421.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 8,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\phong2\269521844.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 8,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\phong2\269521811.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 8,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\phong2\269523668.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 9,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\binhdan\binhdan.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 9,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\binhdan\binhdan3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 10,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\binhdan\binhdan1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 10,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\binhdan\binhdan4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 11,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\caocap\caocap1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 11,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\caocap\caocap3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 12,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\caocap\caocap.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 12,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\caocap\caocap2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 13,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\BinhDan\DPMBResort_DeluxeOceanKing_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 13,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\BinhDan\DPMBResort_DeluxeOceanKing_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 13,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\BinhDan\DPMBResort_DeluxeOceanKing_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 14,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\BinhDan\DPMBResort_DeluxeOceanKing_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 14,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\BinhDan\DPMBResort_DeluxeOceanTwin_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 14,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\BinhDan\DPMBResort_DeluxeOceanTwin_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 15,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\CaoCap\DPMBResort_JuniorSuite_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 15,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\CaoCap\DPMBResort_JuniorSuite_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 15,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\CaoCap\DPMBResort_PremiumDeluxeOceanKing_anh1 20.57.34.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 16,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\CaoCap\DPMBResort_PremiumDeluxeOceanKing_anh3 20.57.35.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 16,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\CaoCap\DPMBResort_JuniorSuite_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 16,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\CaoCap\DPMBResort_ExecutiveSuite_anh3 20.55.44.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 17,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\BinhDan\eden_DeluxeOcean_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 17,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\BinhDan\eden_GrandDeluxeOcean_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 17,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\BinhDan\eden_DeluxeOcean_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 18,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\BinhDan\eden_GrandDeluxeOcean_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 18,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\BinhDan\eden_GrandDeluxeOcean_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 18,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\BinhDan\eden_GrandDeluxeOcean_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 19,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\CaoCap\eden_BungalowBeach_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 19,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\CaoCap\eden_BungalowBeach_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 19,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\CaoCap\eden_BungalowBeach_anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 19,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\CaoCap\eden_BungalowBeach_anh7.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 20,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\BinhDan\LAzure_deluxeTwinBed_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 20,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\BinhDan\LAzure_deluxeTwinBed_anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 20,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\BinhDan\LAzure_deluxeTwinBed_anh9.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 21,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\BinhDan\LAzure_deluxeTwinBed_anh12.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 21,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\BinhDan\LAzure_deluxeTwinBed_anh11.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 21,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\BinhDan\LAzure_deluxeTwinBed_anh13.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 22,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\CaoCap\namnghi_vip_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 22,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\CaoCap\namnghi_vip_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 22,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\CaoCap\namnghi_vip_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 23,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\CaoCap\VinOasis_StandardKing_anh1 22.01.37.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 23,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\CaoCap\VinOasis_StandardKing_anh2 22.01.37.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 24,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\CaoCap\VinOasis_StandardTwin_anh1 22.01.37.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 24,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\CaoCap\VinOasis_StandardTwin_anh2 22.01.37.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 25,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\BinhDan\VinOasis_JuniorSuite_anh1 22.01.22.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 25,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\BinhDan\VinOasis_JuniorSuite_anh2 22.01.22.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 25,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\BinhDan\VinOasis_JuniorSuite_anh3 22.01.22.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 26,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\binhdan\binhdan16.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 26,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\binhdan\binhdan36.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 26,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\binhdan\phongtam26.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 27,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\binhdan\binhdan26.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 27,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\binhdan\binhdan46.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 27,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\caocap\caocap46.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 27,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\caocap\phongtam16.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 28,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\caocap\caocap16.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 28,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\caocap\caocap26.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 28,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\caocap\caocap36.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 29,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiGatsbyHotel\binhdan\binhdan38.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 29,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiGatsbyHotel\caocap\caocap18.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 29,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiGatsbyHotel\caocap\img3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 30,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiGatsbyHotel\caocap\caocap48.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 30,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiGatsbyHotel\caocap\caocap38.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 30,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiGatsbyHotel\caocap\img3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 31,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiGatsbyHotel\binhdan\binhdan28.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 31,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiGatsbyHotel\binhdan\d3d2a8f9_z.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 31,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiGatsbyHotel\binhdan\b2f83f52_z.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 32,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\binhdan\binhdan17.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 32,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\binhdan\binhdan47.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 32,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\binhdan\phongtam27.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 33,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\binhdan\binhdan27.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 33,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\binhdan\binhdan37.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 33,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\binhdan\phongtam27.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 34,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\caocap\caocap17.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 34,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\caocap\caocap47.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 34,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\caocap\phongtam17.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 35,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\caocap\caocap27.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 35,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\caocap\caocap37.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 35,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\caocap\phongtam17.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 36,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\binhdan\binhdan15.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 36,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\binhdan\binhdan25.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 36,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\binhdan\phongtam15.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 37,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\binhdan\binhdan35.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 37,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\binhdan\binhdan45.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 37,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\binhdan\phongtam15.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 38,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\caocap\caocap25.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 38,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\caocap\caocap45.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 38,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\caocap\phongtam25.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 39,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\caocap\caocap15.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 39,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\caocap\caocap35.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 39,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\caocap\phongtam25.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 40,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\BinhDan\LAzure_deluxeTwinBed_anh11.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 40,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\BinhDan\LAzure_deluxeTwinBed_anh10.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 40,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\BinhDan\LAzure_deluxeTwinBed_anh7.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 41,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\BinhDan\LAzure_deluxeTwinBed_anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 41,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\BinhDan\binhdan222.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 41,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\BinhDan\LAzure_deluxeTwinBed_anh14.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 42,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\BinhDan\LAzure_deluxeTwinBed_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 42,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\BinhDan\LAzure_deluxeTwinBed_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 42,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\BinhDan\LAzure_deluxeTwinBed_anh9.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 43,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\binhdan\binhdan14.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 43,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\binhdan\binhdan44.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 43,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\binhdan\phongtam14.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 44,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\binhdan\binhdan24.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 44,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\binhdan\binhdan34.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 44,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\binhdan\phongtam14.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 45,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\caocap\caocap14.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 45,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\caocap\caocap34.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 45,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\caocap\phongtam24.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 46,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\caocap\caocap24.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 46,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\caocap\caocap44.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 46,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\caocap\phongtam24.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 47,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\binhdan\binhdan13.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 47,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\binhdan\binhdan43.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 47,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\binhdan\phongtam13.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 48,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\caocap\caocap23.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 48,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\caocap\caocap33.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 48,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\binhdan\phongtam13.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 49,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\binhdan\binhdan23.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 49,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\binhdan\binhdan33.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 49,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\caocap\phongtam23.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 50,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\caocap\caocap13.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 50,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\caocap\caocap43.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 50,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\caocap\phongtam23.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 51,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\phong1\95412992.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 51,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\phong1\112391668.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 51,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\phong1\95412995.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 51,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\phong1\141658028.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 51,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\phong1\95412309.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 52,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\phong2\170930395.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 52,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\phong2\95412334.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 52,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\phong2\95413220.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 52,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\phong2\95412329.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 53,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\phong1\201220025.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 53,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\phong1\266899781.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 53,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\phong2\201219307.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 54,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\phong1\201220741.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 54,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\phong1\258257702.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 54,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\phong1\201220868.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 55,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\phong2\201220337.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 55,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\phong2\201220491.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 55,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\phong1\201220197.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 56,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\phong2\201220733.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 56,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\phong2\201220868.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 56,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\phong2\201220132.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 57,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\phong2\299875196.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 57,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\phong2\299875241.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 57,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\phong2\299875169.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 58,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\phong1\29987524.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 58,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\phong1\299875211.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 58,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\phong1\196964235.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 59,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\phong1\196992588.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 59,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\phong2\299875155.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 59,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\phong2\299875171.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 60,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong1\phong1_1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 60,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong1\phong1_2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 60,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong1\phong1_3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 60,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong1\phong1_4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 60,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong1\phong1_5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 61,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong2\16588924.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 61,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong2\260483426.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 61,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong2\100072197.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 62,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong3\100073333.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 62,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong3\100075226.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 62,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong3\100075306.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 62,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong3\257915476.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 62,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong3\260482016.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 63,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong4\100072065.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 63,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong4\100075024.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 63,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong4\100075043.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 63,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong4\100075301.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 63,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\phong4\260483426.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 64,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong1\110814582.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 64,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong1\110814893.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 64,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong1\110812995.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 64,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong1\110814592.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 64,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong1\110814922.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 65,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong2\110814898.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 65,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong2\110815301.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 65,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong2\110815306.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 65,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong2\122561617.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 65,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong2\110812998.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 66,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong3\img1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 66,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong3\img2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 66,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong3\img3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 66,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong3\img4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 66,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong3\img5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 67,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\NguyenCanFullHouse\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 67,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\NguyenCanFullHouse\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 67,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\NguyenCanFullHouse\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 67,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\NguyenCanFullHouse\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 67,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\NguyenCanFullHouse\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 68,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\NguyenCanHappyHouse\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 68,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\NguyenCanHappyHouse\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 68,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\NguyenCanHappyHouse\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 68,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\NguyenCanHappyHouse\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 68,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\NguyenCanHappyHouse\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 68,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\NguyenCanHappyHouse\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 69,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\TheRooftop\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 69,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\TheRooftop\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 69,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\TheRooftop\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 69,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\TheRooftop\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 69,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\TheRooftop\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 69,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\TheRooftop\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 70,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\Nha_kinh\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 70,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\Nha_kinh\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 70,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\Nha_kinh\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 70,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\Nha_kinh\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 70,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\Nha_kinh\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 70,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\Nha_kinh\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 71,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Pine Forest Villas\NguyenCan2PhongNgu\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 71,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Pine Forest Villas\NguyenCan2PhongNgu\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 71,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Pine Forest Villas\NguyenCan2PhongNgu\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 71,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Pine Forest Villas\NguyenCan2PhongNgu\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 72,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\Hilltop\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 72,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\Hilltop\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 72,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\Hilltop\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 72,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\Hilltop\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 72,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\Hilltop\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 72,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\Hilltop\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 72,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\Hilltop\anh7.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 73,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\BinhDan\MovenpickResortCamRanh_DeluxeKing_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 73,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\BinhDan\MovenpickResortCamRanh_DeluxeKing_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 73,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\BinhDan\MovenpickResortCamRanh_DeluxeKing_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 73,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\BinhDan\MovenpickResortCamRanh_DeluxeKing_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 73,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\BinhDan\MovenpickResortCamRanh_DeluxeKing_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 74,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\BinhDan\MovenpickResortCamRanh_Superior1_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 74,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\BinhDan\MovenpickResortCamRanh_Superior1_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 74,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\BinhDan\MovenpickResortCamRanh_Superior1_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 74,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\BinhDan\MovenpickResortCamRanh_Superior1_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 74,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\BinhDan\MovenpickResortCamRanh_Superior1_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 75,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\CaoCap\MovenpickResortCamRanh_Villa1_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 75,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\CaoCap\MovenpickResortCamRanh_Villa1_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 75,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\CaoCap\MovenpickResortCamRanh_Villa1_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 75,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\CaoCap\MovenpickResortCamRanh_Villa1_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 76,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeKing_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 76,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeKing_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 76,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeKing_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 76,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeKing_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 76,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeKing_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 76,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeKing_anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 76,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeKing_anh7.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 77,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeOcean_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 77,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeOcean_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 77,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeOcean_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 77,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeOcean_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 77,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeOcean_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 77,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeOcean_anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 77,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\BinhDan\AmianaResortNhaTrang_DeluxeOcean_anh7.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 78,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVilla_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 78,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVilla_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 78,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVilla_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 78,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVilla_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 78,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVilla_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 78,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVilla_anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 78,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVilla_anh7.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 79,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVillaFamily_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 79,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVillaFamily_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 79,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVillaFamily_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 79,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVillaFamily_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 79,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVillaFamily_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 79,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVillaFamily_anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 79,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\CaoCap\AmianaResortNhaTrang_OceanVillaFamily_anh7.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 80,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\BinhDan\VinpearlPQResort_DeluxeKing_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 80,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\BinhDan\VinpearlPQResort_DeluxeKing_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 80,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\BinhDan\VinpearlPQResort_DeluxeTwin_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 80,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\BinhDan\VinpearlPQResort_DeluxeTwin_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 81,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\BinhDan\VinpearlPQResort_DeluxeTwinOceanView_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 81,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\BinhDan\VinpearlPQResort_DeluxeTwinOceanView_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 81,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\BinhDan\VinpearlPQResort_DeluxeTwinOceanView_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 82,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\CaoCap\VinpearlPQResort_ExecutiveSuite_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 82,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\CaoCap\VinpearlPQResort_ExecutiveSuite_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 82,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\CaoCap\VinpearlPQResort_ExecutiveSuite_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 82,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\CaoCap\VinpearlPQResort_ExecutiveSuite_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 82,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\CaoCap\VinpearlPQResort_ExecutiveSuite_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 82,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\CaoCap\VinpearlPQResort_ExecutiveSuite_anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 83,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\BinhDan\RadissonBlu_DeluxeKingView_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 83,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\BinhDan\RadissonBlu_DeluxeKingView_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 83,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\BinhDan\RadissonBlu_DeluxeKingView_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 83,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\BinhDan\RadissonBlu_DeluxeKingView_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 84,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\BinhDan\RadissonBlu_OneBedroomSuite_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 84,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\BinhDan\RadissonBlu_OneBedroomSuite_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 84,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\BinhDan\RadissonBlu_OneBedroomSuite_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 84,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\BinhDan\RadissonBlu_OneBedroomSuite_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 85,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\CaoCap\RadissonBlu_ExecutiveSuite_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 85,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\CaoCap\RadissonBlu_ExecutiveSuite_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 85,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\CaoCap\RadissonBlu_ExecutiveSuite_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 85,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\CaoCap\RadissonBlu_ExecutiveSuite_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 86,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\NguyenCanFullHouse\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 86,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\NguyenCanFullHouse\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 86,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\NguyenCanFullHouse\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 86,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\NguyenCanFullHouse\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 86,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\NguyenCanFullHouse\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 86,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\NguyenCanFullHouse\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 87,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\NguyenCanHappyHouse\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 87,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\NguyenCanHappyHouse\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 87,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\NguyenCanHappyHouse\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 87,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\NguyenCanHappyHouse\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 87,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\NguyenCanHappyHouse\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 87,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\NguyenCanHappyHouse\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 88,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\Nha_kinh\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 88,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\Nha_kinh\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 88,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\Nha_kinh\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 88,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\Nha_kinh\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 88,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\Nha_kinh\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 88,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\Nha_kinh\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 89,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\TheRooftop\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 89,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\TheRooftop\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 89,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\TheRooftop\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 89,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\TheRooftop\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 89,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\TheRooftop\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 89,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\TheRooftop\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 90,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\NguyenCanFullHouse\anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 90,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\NguyenCanFullHouse\anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 90,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\NguyenCanFullHouse\anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 90,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\NguyenCanFullHouse\anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 90,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\NguyenCanFullHouse\anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 90,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\NguyenCanFullHouse\anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 91,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\NguyenCanHappyHouse\anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 91,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\NguyenCanHappyHouse\anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 91,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\NguyenCanHappyHouse\anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 91,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\NguyenCanHappyHouse\anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 91,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\NguyenCanHappyHouse\anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 91,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\NguyenCanHappyHouse\anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 92,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\Nha_kinh\anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 92,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\Nha_kinh\anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 92,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\Nha_kinh\anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 92,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\Nha_kinh\anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 92,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\Nha_kinh\anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 92,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\Nha_kinh\anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 93,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\TheRooftop\anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 93,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\TheRooftop\anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 93,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\TheRooftop\anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 93,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\TheRooftop\anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 93,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\TheRooftop\anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 93,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\TheRooftop\anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 94,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\NguyenCanFullHouse\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 94,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\NguyenCanFullHouse\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 94,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\NguyenCanFullHouse\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 94,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\NguyenCanFullHouse\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 94,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\NguyenCanFullHouse\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 94,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\NguyenCanFullHouse\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 95,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\NguyenCanHappyHouse\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 95,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\NguyenCanHappyHouse\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 95,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\NguyenCanHappyHouse\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 95,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\NguyenCanHappyHouse\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 95,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\NguyenCanHappyHouse\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 95,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\NguyenCanHappyHouse\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 96,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\Nha_Kinh\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 96,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\Nha_Kinh\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 96,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\Nha_Kinh\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 96,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\Nha_Kinh\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 96,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\Nha_Kinh\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 96,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\Nha_Kinh\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 97,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\NguyenCanFullHouse\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 97,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\NguyenCanFullHouse\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 97,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\NguyenCanFullHouse\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 97,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\NguyenCanFullHouse\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 97,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\NguyenCanFullHouse\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 97,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\NguyenCanFullHouse\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 98,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\NguyenCanHappyHouse\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 98,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\NguyenCanHappyHouse\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 98,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\NguyenCanHappyHouse\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 98,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\NguyenCanHappyHouse\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 98,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\NguyenCanHappyHouse\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 98,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\NguyenCanHappyHouse\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 99,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\Nha_kinh\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 99,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\Nha_kinh\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 99,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\Nha_kinh\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 99,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\Nha_kinh\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 99,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\Nha_kinh\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 99,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\Nha_kinh\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 100,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\TheRooftop\anh1.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 100,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\TheRooftop\anh2.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 100,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\TheRooftop\anh3.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 100,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\TheRooftop\anh4.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 100,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\TheRooftop\anh5.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 100,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\TheRooftop\anh6.jpg',Single_Blob) as picture;
insert into HINHANHPHONG
select 101,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\BinhDan\AuroraHomestayDalat_Double_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 101,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\BinhDan\AuroraHomestayDalat_Double_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 101,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\BinhDan\AuroraHomestayDalat_Double_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 101,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\BinhDan\AuroraHomestayDalat_Double_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 101,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\BinhDan\AuroraHomestayDalat_Double_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 102,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\BinhDan\AuroraHomestayDalat_Family_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 102,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\BinhDan\AuroraHomestayDalat_Family_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 102,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\BinhDan\AuroraHomestayDalat_Family_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 102,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\BinhDan\AuroraHomestayDalat_Family_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 102,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\BinhDan\AuroraHomestayDalat_Family_anh5.jpeg',Single_Blob) as picture;

insert into HINHANHPHONG
select 103,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DalatLacasaHomestayIV\BinhDan\DalatLacasaHomestayIV_Arcane1_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 103,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DalatLacasaHomestayIV\BinhDan\DalatLacasaHomestayIV_Arcane1_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 103,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DalatLacasaHomestayIV\BinhDan\DalatLacasaHomestayIV_Arcane1_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 103,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DalatLacasaHomestayIV\BinhDan\DalatLacasaHomestayIV_Arcane1_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 104,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DalatLacasaHomestayIV\BinhDan\DalatLacasaHomestayIV_ArcaneDouble_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 104,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DalatLacasaHomestayIV\BinhDan\DalatLacasaHomestayIV_ArcaneDouble_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 104,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DalatLacasaHomestayIV\BinhDan\DalatLacasaHomestayIV_ArcaneDouble_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 104,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DalatLacasaHomestayIV\BinhDan\DalatLacasaHomestayIV_ArcaneDouble_anh4.jpeg',Single_Blob) as picture;

insert into HINHANHPHONG
select 105,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_EconomyDouble_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 105,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_EconomyDouble_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 105,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_EconomyDouble_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 106,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_Family_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 106,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_Family_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 106,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_Family_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 106,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_Family_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 106,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_Family_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 106,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_Family_anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 107,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_StandardDouble_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 107,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_StandardDouble_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 107,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_StandardDouble_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 107,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_StandardDouble_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 107,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\BinhDan\DocMocHomestay_StandardDouble_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 108,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\BinhDan\HomestaySweetValley_SweetValley_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 108,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\BinhDan\HomestaySweetValley_SweetValley_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 108,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\BinhDan\HomestaySweetValley_SweetValley_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 108,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\BinhDan\HomestaySweetValley_SweetValley_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 108,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\BinhDan\HomestaySweetValley_SweetValley_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 109,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\BinhDan\HomestaySweetValley_SweetValley2_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 109,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\BinhDan\HomestaySweetValley_SweetValley2_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 109,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\BinhDan\HomestaySweetValley_SweetValley2_anh3.jpeg',Single_Blob) as picture;

insert into HINHANHPHONG
select 110,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\BinhDan\LengKengHomestayDalat_Sparking_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 110,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\BinhDan\LengKengHomestayDalat_Sparking_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 110,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\BinhDan\LengKengHomestayDalat_Sparking_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 110,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\BinhDan\LengKengHomestayDalat_Sparking_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 111,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\BinhDan\LengKengHomestayDalat_Special_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 111,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\BinhDan\LengKengHomestayDalat_Special_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 111,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\BinhDan\LengKengHomestayDalat_Special_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 112,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\BinhDan\ZodiacHouseDalat_BasicHill_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 112,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\BinhDan\ZodiacHouseDalat_BasicHill_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 112,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\BinhDan\ZodiacHouseDalat_BasicHill_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 112,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\BinhDan\ZodiacHouseDalat_BasicHill_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 113,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\BinhDan\ZodiacHouseDalat_BasicDoubleHill_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 113,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\BinhDan\ZodiacHouseDalat_BasicDoubleHill_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 113,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\BinhDan\ZodiacHouseDalat_BasicDoubleHill_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 113,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\BinhDan\ZodiacHouseDalat_BasicDoubleHill_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 114,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\BinhDan\ZodiacHouseDalat_FamilyHill_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 114,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\BinhDan\ZodiacHouseDalat_FamilyHill_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 114,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\BinhDan\ZodiacHouseDalat_FamilyHill_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 114,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\BinhDan\ZodiacHouseDalat_FamilyHill_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 115,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MayNgangDoiHomestay\BinhDan\MayNgangDoiHomestay_PhongDouble_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 115,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MayNgangDoiHomestay\BinhDan\MayNgangDoiHomestay_PhongDouble_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 115,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MayNgangDoiHomestay\BinhDan\MayNgangDoiHomestay_PhongDouble_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 115,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MayNgangDoiHomestay\BinhDan\MayNgangDoiHomestay_PhongDouble_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 116,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MayNgangDoiHomestay\BinhDan\MayNgangDoiHomestay_PhongFamilyWithBalcony_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 116,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MayNgangDoiHomestay\BinhDan\MayNgangDoiHomestay_PhongFamilyWithBalcony_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 116,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MayNgangDoiHomestay\BinhDan\MayNgangDoiHomestay_PhongFamilyWithBalcony_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 116,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MayNgangDoiHomestay\BinhDan\MayNgangDoiHomestay_PhongFamilyWithBalcony_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 116,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MayNgangDoiHomestay\BinhDan\MayNgangDoiHomestay_PhongFamilyWithBalcony_anh5.jpeg',Single_Blob) as picture;

insert into HINHANHPHONG
select 117,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RaccoonPawsHomestayDalat\BinhDan\RaccoonPawsHomestayDalat_StandardDouble_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 117,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RaccoonPawsHomestayDalat\BinhDan\RaccoonPawsHomestayDalat_StandardDouble_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 117,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RaccoonPawsHomestayDalat\BinhDan\RaccoonPawsHomestayDalat_StandardDouble_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 117,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RaccoonPawsHomestayDalat\BinhDan\RaccoonPawsHomestayDalat_StandardDouble_anh4.jpeg',Single_Blob) as picture;

insert into HINHANHPHONG
select 118,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RaccoonPawsHomestayDalat\BinhDan\RaccoonPawsHomestayDalat_TriperDouble_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 118,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RaccoonPawsHomestayDalat\BinhDan\RaccoonPawsHomestayDalat_TriperDouble_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 118,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RaccoonPawsHomestayDalat\BinhDan\RaccoonPawsHomestayDalat_TriperDouble_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 118,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RaccoonPawsHomestayDalat\BinhDan\RaccoonPawsHomestayDalat_TriperDouble_anh4.jpeg',Single_Blob) as picture;

insert into HINHANHPHONG
select 119,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_HillTop_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 119,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_HillTop_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 119,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_HillTop_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 119,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_HillTop_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 119,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_HillTop_anh5.jpeg',Single_Blob) as picture;

insert into HINHANHPHONG
select 120,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_PoolVillaBeachfront_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 120,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_PoolVillaBeachfront_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 120,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_PoolVillaBeachfront_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 120,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_PoolVillaBeachfront_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 120,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_PoolVillaBeachfront_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 120,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_PoolVillaBeachfront_anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 120,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_PoolVillaBeachfront_anh7.jpeg',Single_Blob) as picture;

insert into HINHANHPHONG
select 121,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_RockPoolVilla_anh1.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 121,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_RockPoolVilla_anh2.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 84,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_RockPoolVilla_anh3.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 121,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_RockPoolVilla_anh4.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 121,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_RockPoolVilla_anh5.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 121,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_RockPoolVilla_anh6.jpeg',Single_Blob) as picture;
insert into HINHANHPHONG
select 121,BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\CaoCap\SixSensesNinhVanBay_RockPoolVilla_anh7.jpeg',Single_Blob) as picture;





select * from KHACHHANG
delete from KHACHHANG
DBCC CHECKIDENT ('KHACHHANG',RESEED,0)
insert into KHACHHANG (Email,UserName,Pass,DiaChi,SDT,GioiTinh,CMND,ISADMIN,Anh)
select 'admin@fpt.edu.vn','admin','admin',N'Cao đẳng FPT Polytechnic','88888888',1,'123456789',1,
BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\phong3\img5.jpg',Single_Blob) as picture;


select * from CHUKHACHSAN
delete from CHUKHACHSAN
DBCC CHECKIDENT ('CHUKHACHSAN',RESEED,0)
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 1,N'Nguyễn Thanh Toàn','0356666392',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\CharmingHotelHaNoi\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 2,N'Đào Thu Anh','0919905889',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Risemount_Premier_Resort_DaNang\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 3,N'Long Trương Định','0358888567',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ConiferGrandHanoi\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 4,N'Nguyễn Trung Bằng','0358888567',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DusitPrincessMoonriseBeachResort\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 5,N'Vũ Anh Tuấn','0946657552',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Eden\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 6,N'Hà Thanh Tùng','0357777251',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\NamNghi\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 7,N'Đặng Đình Vũ','0949948888',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinOasis\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 8,N'Vũ Xuân Nam','0358881234',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiCrystalHotel\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 9,N'Lê Hà Khánh','0356669142',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HanoiGatsbyHotel\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 10,N'Nguyễn Thị Đặng Phương','0357676352',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanGoldenSilk\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 11,N'Hoàng Ngọc Khánh','0359999371',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\KhachsanImperialSpaHaNoi\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 12,N'Nguyễn Huy Hoàng','0987787798',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LAzureResortAndSpa\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 13,N'Hoàng Nghĩa Đạt','0351111638',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SofitelLegendMetropoleHotel\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 14,N'Nguyễn Thị Minh Anh','0989967895',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ThePalmyHotelSpa\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 15,N'Đào Minh Ánh','0949946658',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mangala_Zen_Garden_&_Luxury_Apartments\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 16,N'Tạ Thị Hằng','0358341855',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Novotel_Danang_Premier_Han_River\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 17,N'Nguyễn Gia Bảo','0359898537',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MuongThanh_Luxury_Song_Han_Hotel\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 18,N'Nguyễn Quang Duy','0984685572',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\InterContinental_Danang_Sun_Peninsula_Resort\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 19,N'Vũ Đức Mạnh','0565779888',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Altara_Suites_by_ Ri-Yaz\avatar_chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 20,N'Nguyễn Hoàng','056588888',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Choai Villa Sóc Sơn\anhchu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 21,N'Phạm Khánh Linh','0358241898',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\The Pine Forest Villas\anhchu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 22,N'Phùng Tiến Đức','0984045729',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hilltop Villa\anhchu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 23,N'Nguyễn Hải Đăng','0878885220',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MovenpickResortCamRanh\anhchu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 24,N'Nguyễn Hoàng Việt Khoa','0878885220',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AmianaResortNhaTrang\anhchu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 25,N'Nguyễn Ngọc Lan Anh','0888888888',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\VinpearlPhuQuocesort\anhchu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 26,N'lê Đình Hiếu','0888887788',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RadissonBluResort\anhchu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 27,N'Bùi Thị Hương','0998752652',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mercure Hanoi la Gare\anhchu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 28,N'Trần Bá Dương','0998755552',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Mayflower Hotel Hanoi\anhchu.png',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 29,N'Christopher','0998755552',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Khách sạn Mövenpick Hà Nội\anhchu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 30,N'Nguyễn Đình Trung','0358882122',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\Hanoi La Storia Hotel\anhchu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 31,N'Hoàng Quốc Trường','0565779828',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\AuroraHomestayDalat\chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 32,N'Hoàng Thiên Mệnh ','0565779228',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DalatLacasaHomestayIV\chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 33,N'Hoàng Chu Bá ','0565773828',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\DocMocHomestay\chu.png',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 34,N'Đinh Mạnh Hùng ','0543773828',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\HomestaySweetValley\chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 35,N'Hà Tiến Đạt ','0543773333',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\LengKengHomestayDalat\chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 36,N'Bùi Anh Tuấn','0543555333',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\ZodiacHouseDalat\chu.jpg',Single_Blob) as picture;
insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 37,N'Bùi Mạnh Nghĩa ','0541273333',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\MayNgangDoiHomestay\chu.jpg',Single_Blob) as picture;

insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 38,N'Lý Cát Thy ','0541279898',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\RaccoonPawsHomestayDalat\chu.jpg',Single_Blob) as picture;

insert into CHUKHACHSAN (MaKS,Ten,SDT,Anh)
select 39,N'Đặng vũ Dũng ','0549999338',BulkColumn from Openrowset (Bulk 'C:\image_myvntour\SixSensesNinhVanBay\chu.png',Single_Blob) as picture;

select * from CHECKTT
delete from CHECKTT
DBCC CHECKIDENT ('CHECKTT',RESEED,0)
insert into CHECKTT
values	(1,1,0),
		(2,1,0),
		(3,1,0),
		(4,1,0),
		(5,1,0),
		(6,1,0),
		(7,1,0),
		(8,1,0),
		(9,1,0),
		(10,1,0),
		(11,1,0),
		(12,1,0),
		(13,1,0),
		(14,1,0),
		(15,1,0),
		(16,1,0),
		(17,1,0),
		(18,1,0),
		(19,1,0),
		(20,1,0),
		(21,1,0),
		(22,1,0),
		(23,1,0),
		(24,1,0),
		(25,1,0),
		(26,1,0),
		(27,1,0),
		(28,1,0),
		(29,1,0),
		(30,1,0),
		(31,1,0),
		(32,1,0),
		(33,1,0),
		(34,1,0),
		(35,1,0),
		(36,1,0),
		(37,1,0),
		(38,1,0),
		(39,1,0)

