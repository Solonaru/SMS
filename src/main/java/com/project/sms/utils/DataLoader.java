package com.project.sms.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.project.sms.entities.account.IAccountService;
import com.project.sms.entities.catalogue.Catalogue;
import com.project.sms.entities.catalogue.CatalogueItem;
import com.project.sms.entities.catalogue.ICatalogueItemService;
import com.project.sms.entities.catalogue.ICatalogueService;
import com.project.sms.entities.category.Category;
import com.project.sms.entities.category.ICategoryService;
import com.project.sms.entities.customer.Customer;
import com.project.sms.entities.employee.Admin;
import com.project.sms.entities.employee.Employee;
import com.project.sms.entities.employee.IRight;
import com.project.sms.entities.employee.OperatorProducts;
import com.project.sms.entities.item.Hardware;
import com.project.sms.entities.item.IItemService;
import com.project.sms.entities.item.Item;
import com.project.sms.entities.item.Software;
import com.project.sms.entities.lines.LineFactory;
import com.project.sms.entities.lines.PackageLineFactory;
import com.project.sms.entities.lines.RecipeLineFactory;
import com.project.sms.entities.location.Address;
import com.project.sms.entities.location.AddressBuilder;
import com.project.sms.entities.location.City;
import com.project.sms.entities.location.County;
import com.project.sms.entities.location.IAddressService;
import com.project.sms.entities.location.ICityService;
import com.project.sms.entities.location.ICountyService;
import com.project.sms.entities.order.Cart;
import com.project.sms.entities.order.CartLine;
import com.project.sms.entities.order.ICartLineService;
import com.project.sms.entities.order.ICartService;
import com.project.sms.entities.order.IOrderService;
import com.project.sms.entities.order.IPaymentService;
import com.project.sms.entities.order.Orders;
import com.project.sms.entities.order.Payment;
import com.project.sms.entities.pack.IPackageLineService;
import com.project.sms.entities.pack.Package;
import com.project.sms.entities.pack.PackageLine;
import com.project.sms.entities.recipe.IRecipeLineService;
import com.project.sms.entities.recipe.Recipe;
import com.project.sms.entities.recipe.RecipeLine;
import com.project.sms.entities.subscription.ISubscriptionService;
import com.project.sms.entities.subscription.Subscription;
import com.project.sms.enums.ComponentType;
import com.project.sms.enums.EmployeeStatus;
import com.project.sms.enums.SubscriptionType;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ICountyService countyService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private IAddressService addressService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private ISubscriptionService subscriptionService;
	@Autowired
	private IItemService itemService;
	@Autowired
	private IRecipeLineService recipeLineService;
	@Autowired
	private IPackageLineService packageLineService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private ICatalogueService catalogueService;
	@Autowired
	private ICatalogueItemService catalogueItemService;
	@Autowired
	private ICartLineService cartLineService;
	@Autowired
	private ICartService cartService;
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IPaymentService paymentService;

	@Autowired
	private DisplayData displayData;
	@Autowired
	private ObjectGenerator objectGenerator;
	@Autowired
	private LineFactory lineFactory;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (isDBEmpty()) {
			loadData();
		}

//		System.out.println("------------------------------------------------------");
//
//		Item item = itemService.findItemById(4).get();
//		System.out.println("Is the item listed: " + item.isListed());
//		System.out.println("The price of the item is: " + item.getPrice(Month.DECEMBER));
//
//		Employee employee = (Employee) accountService.findAccountById(400000005).get();
//		System.out.println("EMPLOYEE CAN CREATE CATEGORY: " + employee.getRightType().canCreateCategory());
//		System.out.println("EMPLOYEE HAS CREATE PRODUCT: " + employee.getRightType().canCreateProduct());
//
//		System.out.println("------------------------------------------------------");
//
//		Package package1 = (Package) itemService.findItemById(26).get();
//		Iterator<? extends ILine> iterator1 = package1.createIterator();
//
//		while (iterator1.hasNext()) {
//			PackageLine packageLine = (PackageLine) iterator1.next();
//			System.out.println("PACKAGE LINE: " + packageLine.getProduct().getName());
//		}
//
//		System.out.println("------------------------------------------------------");
//
//		Recipe recipe1 = (Recipe) itemService.findItemById(24).get();
//		Iterator<? extends ILine> iterator2 = recipe1.createIterator();
//
//		while (iterator2.hasNext()) {
//			RecipeLine recipeLine = (RecipeLine) iterator2.next();
//			System.out.println("RECIPE LINE: " + recipeLine.getComponent().getName());
//		}
//
//		System.out.println("------------------------------------------------------");

	}

	@SuppressWarnings("unchecked")
	private void loadData() {
		displayData.printInfo("Starting data loading...");

		County county = new County("Iasi", "Moldova");

		City city1 = new City("Iasi");
		city1.setCounty(county);
		City city2 = new City("Pascani");
		city2.setCounty(county);
		City city3 = new City("Targu Frumos");
		city3.setCounty(county);

		Address address1 = new AddressBuilder("Alexandru cel Bun", 51).setApartamentNr(15).setZipCode(270123)
				.getAddress();
		address1.setCity(city1);
		Address address2 = new AddressBuilder("Marinelor", 11).setZipCode(280129).getAddress();
		address2.setCity(city2);
		Address address3 = new AddressBuilder("Marinelor", 27).setBuildingNr("B").setZipCode(280129).getAddress();
		address3.setCity(city2);
		Address address4 = new AddressBuilder("Marin Cordoba", 100).setZipCode(290111).getAddress();
		address4.setCity(city3);
		Address address5 = new AddressBuilder("Cosmopolitan", 127).setBuildingNr("3").setApartamentNr(205)
				.setFloorNr("2").setZipCode(270123).getAddress();
		address5.setCity(city1);
		Address address6 = new AddressBuilder("Cosmopolitan", 129).setZipCode(270123).getAddress();
		address6.setCity(city1);
		Address address7 = new AddressBuilder("Cereselor", 12).setApartamentNr(115).setFloorNr("GF").setZipCode(270323)
				.getAddress();
		address7.setCity(city1);
		Address address8 = new AddressBuilder("Independentei", 30).setZipCode(278012).getAddress();
		address8.setCity(city1);

		Subscription subscription1 = new Subscription(SubscriptionType.DISCOUNTS);
		Subscription subscription2 = new Subscription(SubscriptionType.NEW_PRODUCTS);
		Subscription subscription3 = new Subscription(SubscriptionType.PROMOTIONAL);

		Customer customer1 = new Customer("Alex", "alex123", "Alexandru", "alex_cozma@gmail.com", "0748974419");
		customer1.setAddress(address1);
		customer1.getDeliveryAddresses().add(address5);
		customer1.getDeliveryAddresses().add(address6);
		customer1.getSubscriptions().add(subscription1);
		customer1.getSubscriptions().add(subscription3);
		Customer customer2 = new Customer("Buzzy23", "password", "Maxim", "max_96@yahoo.com", "0742114411");
		customer2.setAddress(address2);
		Customer customer3 = new Customer("Alinacika", "saalina", "Alina", "alina_sandra@gmail.com", "0742271410");
		customer3.setAddress(address3);
		customer3.getDeliveryAddresses().add(address4);
		customer3.getSubscriptions().add(subscription1);
		customer3.getSubscriptions().add(subscription2);
		customer3.getSubscriptions().add(subscription3);

		// ------------------------------------------------------------------- //

		IRight admin = new Admin();
		IRight operatorProducts = new OperatorProducts();

		Employee employee1 = new Employee("viorel", "viorel123", "Viorel", "viorelsolonaru@gmail.com", "0748974417",
				EmployeeStatus.SENIOR, admin);
		employee1.setAddress(address7);
		Employee employee2 = new Employee("andrei", "andrei123", "Andrei", "andreihumulescu@gmail.com", "0721314417",
				EmployeeStatus.INTERNSHIP, operatorProducts);
		employee2.setAddress(address8);

		// ------------------------------------------------------------------- //

		Category category1 = new Category("Laptops", new Date(System.currentTimeMillis()),
				"We provide a wide range of laptops, starting from small-sized laptops to gaming ones.");
		Category category2 = new Category("Software", new Date(System.currentTimeMillis()),
				"Here at TECHnique, you can find a variety of software tools, such as business softwares, "
						+ "security softwares, development and testing softwares and many many more you can choose from!");
		Category category3 = new Category("Components", new Date(System.currentTimeMillis()),
				"Because every detail counts when it comes to computer hardware components,"
						+ " we offer you teh opportunity to choose the tools you need. "
						+ "In this category you can find the best CPUs, mothrboards, external memory,"
						+ " ports, expansion softs, secondary storage, input-output devices, "
						+ "communication devices etc. taht would best suit your needs. ");
		Category category301 = new Category("Motherboards", new Date(System.currentTimeMillis()),
				"Motherboards come in different sizes, known as form factors, such as ATX, "
						+ "micro-ATX FlexATX,  EATX, nano-ATX, mobile-ATX, ITX aka mini-ATX/nano-ATX/pico-ATX, NLX, LPX. "
						+ "You can find on our store every kind of motherboard is best for you.");
		category301.setParentCategory(category3);
		Category category302 = new Category("CPUs", new Date(System.currentTimeMillis()),
				"There are many types of CPUs on the market, but there are only a few that you should consider purchasing. "
						+ "Reason for, our store is selling only high-quality products. "
						+ "Single Core CPUs, Dual Core CPUs, Quad Core CPUs from prestigious brands like Intel or AMD");
		category302.setParentCategory(category3);
		Category category303 = new Category("RAMs", new Date(System.currentTimeMillis()),
				"RAM comes in a variety of shapes, capacities (measured in MB or GB),"
						+ " speeds (measured in MHx or GHz), and architectures. "
						+ "TECHnique is offering you 7 types of RAM: Static RAM (SRAM), Dynamic RAM (DRAM), "
						+ "Synchronous Dynamic RAM (SDRAM), Single Data Synchronous Dynamic RAM (SDR SDRAM),"
						+ "Double Data Synchronous Dynamic RAM (DDR SDRAM), Graphics Double Data Synchronous Dynamic RAM "
						+ "(GDDR SDRAM, GDDR2, GDDR3, GDDR4, GDDR5), " + "Flash Memory");
		category303.setParentCategory(category3);
		Category category304 = new Category("Hard drives", new Date(System.currentTimeMillis()),
				"HDDs are different in terms of capacity, size, shape, internal structure, "
						+ "performance, interface and modes of storing data. "
						+ "We provide a large range of HDDs of four major types: Parallel Advanced Technology Attatchment (PATA), "
						+ "Serial ATA (SATA), Small Computer System Interface (SCSI), " + "Solid State Drivers (SSD).");
		category304.setParentCategory(category3);
		Category category305 = new Category("Power supply units", new Date(System.currentTimeMillis()),
				"Since a computer is full of different types of electronic circuits, though, several"
						+ " of which operate at different voltages, a simple connection to a wall wil not cover "
						+ "its needs. TECHnique privides an impressive range of PC Power Supply Units, such as"
						+ " Desktop Power Supplies, Laptop Power Supplies, Power Saving Modes, ATX Power Supplies,"
						+ " ATX Supported Voltages. ");
		category305.setParentCategory(category3);
		Category category306 = new Category("Video cards", new Date(System.currentTimeMillis()),
				"Many computers come wit on-board, buid-in or graphics cards. ann additional video card will "
						+ "improve the quality of video games or video, but it is important to know which type of "
						+ "graphics card is the most suitable for your computer (On-Board, PCI Express,"
						+ " AGP, External Graphics Cards, Legacy Graphics Cards. ) "
						+ "TECHnique offers you the latest GPUs from prestigious brands like NVidia, MSI, Zotac, AMD, XFX. ");
		category306.setParentCategory(category3);
		Category category307 = new Category("Sound cards", new Date(System.currentTimeMillis()),
				"Looking to upgrade you Pc/laptop? "
						+ "Shop a wide selection of Sound Cards from brands like Asus, Creative Labs, StarTech and mode!");
		category307.setParentCategory(category3);
		Category category308 = new Category("Network cards", new Date(System.currentTimeMillis()),
				"Shop  a wide selection of PCI and PCI Express Network Adapter Cards from known privides like TP-Link, Intel and more. ");
		category308.setParentCategory(category3);

		Category category4 = new Category("Accessories", new Date(System.currentTimeMillis()),
				"Deiscover the perfect tech accesoories for you and your friends at TECHnique. "
						+ "Add some glitz and glamour to your lives with our impressive collection of accesories (headphones, mouses, keyboards). ");
		Category category401 = new Category("Headphones", new Date(System.currentTimeMillis()),
				"Trolling the internet to find teh best headphones at the best price is a real struggle. "
						+ "TECHnique eases your work and presents you the newest models with the newest features to keep you up to date with the new technologies."
						+ " PUSH YOUR LIMITS WITH HEADPHONES THAT HAVE NONE!");
		category401.setParentCategory(category4);
		Category category402 = new Category("Mouses", new Date(System.currentTimeMillis()),
				"Browse through a wide range of computer mouses online from brands such as HP, Ligitech, iBall, "
						+ "Targus, Serioux, Hama and many many more. "
						+ "Cable mouses, wireless mouses, mechanical mouses, optical mouses, IR mouses, mouses with buttons, "
						+ "trackball mouses, stylus mouses and cordless 3D mouses... all available on TECHnique. ");
		category402.setParentCategory(category4);
		Category category403 = new Category("Keyboards", new Date(System.currentTimeMillis()),
				"Buy normal or wireless keyboards online from TECHnique. Shop from brands like  Microsoft,"
						+ " Samsung, iBall, Srioux, Dell and many more. ");
		category403.setParentCategory(category4);

		Category category5 = new Category("Packages", new Date(System.currentTimeMillis()),
				"This is a special category of products taht TECHnique offers. You have the opportunity to make your own computer, "
						+ "being able to chose the components that you wish your computer to have. Give it a try and start building your own personal PC. ");

		// ------------------------------------------------------------------- //

		Software software1 = new Software("Windows 10 Professional", 20, new Date(System.currentTimeMillis()), "", "");
		software1.setCategory(category2);
		software1.setImageUrl("../../../../assets/images/items/products/components/softwares/software1.jpg");
		Software software2 = new Software("Windows 10 Home", 20, new Date(System.currentTimeMillis()), "", "");
		software2.setCategory(category2);
		software2.setImageUrl("../../../../assets/images/items/products/components/softwares/software2.jpg");
		Software software3 = new Software("Windows 7 Professional", 15, new Date(System.currentTimeMillis()), "", "");
		software3.setCategory(category2);
		software3.setImageUrl("../../../../assets/images/items/products/components/softwares/software3.jpg");
		Software software4 = new Software("Windows 8 ProPack", 100, new Date(System.currentTimeMillis()), "", "");
		software4.setCategory(category2);
		software4.setImageUrl("../../../../assets/images/items/products/components/softwares/software4.jpg");

		Hardware hardware1 = new Hardware("Motherboard: M.2 B250 mATX", 12, new Date(System.currentTimeMillis()), "");
		hardware1.setCategory(category301);
		hardware1.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware1.jpg");
		Hardware hardware2 = new Hardware("CPU: Intel Core i7-8700K", 16, new Date(System.currentTimeMillis()), "");
		hardware2.setCategory(category302);
		hardware2.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware2.jpg");
		Hardware hardware3 = new Hardware("CPU: AMD Ryzen 7", 9, new Date(System.currentTimeMillis()), "");
		hardware3.setCategory(category302);
		hardware3.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware3.jpg");
		Hardware hardware4 = new Hardware("RAM 16GB", 10, new Date(System.currentTimeMillis()), "");
		hardware4.setCategory(category303);
		hardware4.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware4.jpg");
		Hardware hardware5 = new Hardware("RAM 8GB", 43, new Date(System.currentTimeMillis()), "");
		hardware5.setCategory(category303);
		hardware5.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware5.jpg");
		Hardware hardware6 = new Hardware("RAM 4GB", 32, new Date(System.currentTimeMillis()), "");
		hardware6.setCategory(category303);
		hardware6.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware6.jpg");
		Hardware hardware7 = new Hardware("Hard drive: Seagate Barracuda", 32, new Date(System.currentTimeMillis()),
				"");
		hardware7.setCategory(category304);
		hardware7.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware7.jpg");
		Hardware hardware8 = new Hardware("Power supply unit: Seasonic Focus", 15, new Date(System.currentTimeMillis()),
				"");
		hardware8.setCategory(category305);
		hardware8.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware8.jpg");
		Hardware hardware9 = new Hardware("Video card: MSI GeForce GTX 1070", 12, new Date(System.currentTimeMillis()),
				"");
		hardware9.setCategory(category306);
		hardware9.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware9.jpg");
		Hardware hardware10 = new Hardware("Video card: Gigabyte Geforce GTX 1050", 31,
				new Date(System.currentTimeMillis()), "");
		hardware10.setCategory(category306);
		hardware10.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware10.jpg");
		Hardware hardware11 = new Hardware("Video card: EVGA GeForce GTX 1060", 17,
				new Date(System.currentTimeMillis()), "");
		hardware11.setCategory(category306);
		hardware11.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware11.jpg");
		Hardware hardware12 = new Hardware("Sound card: Intel G3260 3MB Haswell Dual-Core 3.3 GHz", 19,
				new Date(System.currentTimeMillis()), "");
		hardware12.setCategory(category307);
		hardware12.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware12.jpg");
		Hardware hardware13 = new Hardware("Network card: Thecus C10GTR 10GbE Network Interface Card", 12,
				new Date(System.currentTimeMillis()), "");
		hardware13.setCategory(category308);
		hardware13.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware13.jpg");

		Hardware hardware14 = new Hardware("Headphones: Skull Candy", 40, new Date(System.currentTimeMillis()), "");
		hardware14.setCategory(category401);
		hardware14.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware14.jpg");
		Hardware hardware15 = new Hardware("Headphones: Philips", 20, new Date(System.currentTimeMillis()), "");
		hardware15.setCategory(category401);
		hardware15.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware15.jpg");
		Hardware hardware16 = new Hardware("Mouse: Corssair 2018", 13, new Date(System.currentTimeMillis()), "");
		hardware16.setCategory(category402);
		hardware16.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware16.jpg");
		Hardware hardware17 = new Hardware("Mouse: Corssair K20", 33, new Date(System.currentTimeMillis()), "");
		hardware17.setCategory(category402);
		hardware17.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware17.jpg");
		Hardware hardware18 = new Hardware("Mouse: Corssair Z92", 12, new Date(System.currentTimeMillis()), "");
		hardware18.setCategory(category402);
		hardware18.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware18.jpg");
		Hardware hardware19 = new Hardware("Keyboard: Logitech K800", 51, new Date(System.currentTimeMillis()), "");
		hardware19.setCategory(category403);
		hardware19.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware19.jpg");

		// ----------- products to create -------------------
		Hardware hardware20 = new Hardware("iStarUSA TC-2U/50-80", 23, new Date(System.currentTimeMillis()), "");
		hardware20.setCategory(category305);
		hardware20.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware20.jpg");
		Hardware hardware21 = new Hardware("T10 Gaming Mouse 2400", 73, new Date(System.currentTimeMillis()), "");
		hardware21.setCategory(category402);
		hardware21.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware21.jpg");
		Hardware hardware22 = new Hardware("Rosewill NEON M60 RGB", 50, new Date(System.currentTimeMillis()), "");
		hardware22.setCategory(category402);
		hardware22.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware22.jpg");
		Hardware hardware23 = new Hardware("Razer Basilisk-RZ01-02330100-R3U1", 37,
				new Date(System.currentTimeMillis()), "");
		hardware23.setCategory(category402);
		hardware23.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware23.jpg");
		Hardware hardware24 = new Hardware("RGB Gaming Mouse - CM-310-KKWO2", 45, new Date(System.currentTimeMillis()),
				"");
		hardware24.setCategory(category402);
		hardware24.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware24.jpg");
		Hardware hardware25 = new Hardware("Rosewill NEON M55 - 6000 dpi RGB", 54, new Date(System.currentTimeMillis()),
				"");
		hardware25.setCategory(category402);
		hardware25.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware25.jpg");
		Hardware hardware26 = new Hardware("Logitech G203 Prodigy Wired", 77, new Date(System.currentTimeMillis()), "");
		hardware26.setCategory(category402);
		hardware26.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware26.jpg");
		Hardware hardware27 = new Hardware("Logitech G703 LIGHTSPEED Wireless", 85,
				new Date(System.currentTimeMillis()), "");
		hardware27.setCategory(category402);
		hardware27.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware27.jpg");
		Hardware hardware28 = new Hardware("Logitech POWERPLAY 910-005270", 90, new Date(System.currentTimeMillis()),
				"");
		hardware28.setCategory(category402);
		hardware28.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware28.jpg");
		Hardware hardware29 = new Hardware("Rosewill NEON M59", 65, new Date(System.currentTimeMillis()), "");
		hardware29.setCategory(category402);
		hardware29.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware29.jpg");
		Hardware hardware30 = new Hardware("Logitech G703 LIGHTSPEED-White", 50, new Date(System.currentTimeMillis()),
				"");
		hardware30.setCategory(category402);
		hardware30.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware30.jpg");

		Hardware hardware31 = new Hardware("Professional Gaming Keyboard 8 LED", 50,
				new Date(System.currentTimeMillis()), "");
		hardware31.setCategory(category403);
		hardware31.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware31.jpg");
		Hardware hardware32 = new Hardware("Gigabyte Mechanical Keyboard (GK-FORCE K83 RED)", 100,
				new Date(System.currentTimeMillis()), "");
		hardware32.setCategory(category403);
		hardware32.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware32.jpg");
		Hardware hardware33 = new Hardware("Corsair K95 RGB PLATINUM Mechanical", 99,
				new Date(System.currentTimeMillis()), "");
		hardware33.setCategory(category403);
		hardware33.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware33.jpg");
		Hardware hardware34 = new Hardware("Corsair K95 RGB Mechanical", 50, new Date(System.currentTimeMillis()), "");
		hardware34.setCategory(category403);
		hardware34.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware34.jpg");
		Hardware hardware35 = new Hardware("Logitech G910 Orion Spark RGB Mechanical", 48,
				new Date(System.currentTimeMillis()), "");
		hardware35.setCategory(category403);
		hardware35.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware35.jpg");
		Hardware hardware36 = new Hardware("Corsair K63 Keyboard & Lapboard Combo", 215,
				new Date(System.currentTimeMillis()), "");
		hardware36.setCategory(category403);
		hardware36.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware36.jpg");
		Hardware hardware37 = new Hardware("Razer Huntsman Elite", 87, new Date(System.currentTimeMillis()), "");
		hardware37.setCategory(category403);
		hardware37.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware37.jpg");
		Hardware hardware38 = new Hardware("Patriot Viper V730", 120, new Date(System.currentTimeMillis()), "");
		hardware38.setCategory(category403);
		hardware38.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware38.jpg");
		Hardware hardware39 = new Hardware("Devastator II LED Gaming Keyboard and Mouse Combo", 200,
				new Date(System.currentTimeMillis()), "");
		hardware39.setCategory(category403);
		hardware39.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware39.jpg");
		Hardware hardware40 = new Hardware("Tt eSPORTS Challenger", 95, new Date(System.currentTimeMillis()), "");
		hardware40.setCategory(category403);
		hardware40.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware40.jpg");

		Hardware hardware41 = new Hardware("KEF Porsche Design SPACE ONE", 200, new Date(System.currentTimeMillis()),
				"");
		hardware41.setCategory(category401);
		hardware41.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware41.jpg");
		Hardware hardware42 = new Hardware("Philips SHB5950WT/27 Bluetooth Headphones", 185,
				new Date(System.currentTimeMillis()), "");
		hardware42.setCategory(category401);
		hardware42.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware42.jpg");
		Hardware hardware43 = new Hardware("Philips SHB3075BK BASS", 170, new Date(System.currentTimeMillis()), "");
		hardware43.setCategory(category401);
		hardware43.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware43.jpg");
		Hardware hardware44 = new Hardware("Bose QuietComfort 35", 150, new Date(System.currentTimeMillis()), "");
		hardware44.setCategory(category401);
		hardware44.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware44.jpg");
		Hardware hardware45 = new Hardware("Klipsch Reference On-Ear II", 100, new Date(System.currentTimeMillis()),
				"");
		hardware45.setCategory(category401);
		hardware45.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware45.jpg");
		Hardware hardware46 = new Hardware("Beats Studio3", 300, new Date(System.currentTimeMillis()), "");
		hardware46.setCategory(category401);
		hardware46.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware46.jpg");
		Hardware hardware47 = new Hardware("Beats Studio3 Wireless", 490, new Date(System.currentTimeMillis()), "");
		hardware47.setCategory(category401);
		hardware47.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware47.jpg");
		Hardware hardware48 = new Hardware("Bose SoundSport Free Truly", 300, new Date(System.currentTimeMillis()), "");
		hardware48.setCategory(category401);
		hardware48.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware48.jpg");
		Hardware hardware49 = new Hardware("Sennheiser HD 4.50 BTNC", 90, new Date(System.currentTimeMillis()), "");
		hardware49.setCategory(category401);
		hardware49.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware49.jpg");
		Hardware hardware50 = new Hardware("Sony MDR-ZX220BT", 80, new Date(System.currentTimeMillis()), "");
		hardware50.setCategory(category401);
		hardware50.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware50.jpg");

		Hardware hardware51 = new Hardware("GIGABYTE Z390 AORUS XTREME LGA 1151", 550,
				new Date(System.currentTimeMillis()), "");
		hardware51.setCategory(category301);
		hardware51.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware51.jpg");
		Hardware hardware52 = new Hardware("MSI MEG Z390 GODLIKE LGA 1151", 565, new Date(System.currentTimeMillis()),
				"");
		hardware52.setCategory(category301);
		hardware52.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware52.jpg");
		Hardware hardware53 = new Hardware("ASUS ROG Maximus XI Extreme LGA 1151", 600,
				new Date(System.currentTimeMillis()), "");
		hardware53.setCategory(category301);
		hardware53.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware53.jpg");
		Hardware hardware54 = new Hardware("ASRock Z390 Phantom Gaming 9 LGA 1151", 270,
				new Date(System.currentTimeMillis()), "");
		hardware54.setCategory(category301);
		hardware54.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware54.jpg");
		Hardware hardware55 = new Hardware("MSI MEG X399 CREATION sTR4 AMD X399", 550,
				new Date(System.currentTimeMillis()), "");
		hardware55.setCategory(category301);
		hardware55.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware55.jpg");
		Hardware hardware56 = new Hardware("Intel Core i7-8700K Coffee Lake", 370, new Date(System.currentTimeMillis()),
				"");
		hardware56.setCategory(category302);
		hardware56.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware56.jpg");
		Hardware hardware57 = new Hardware("AMD RYZEN 7 2700X 8-Core 3.7 GHz", 310,
				new Date(System.currentTimeMillis()), "");
		hardware57.setCategory(category302);
		hardware57.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware57.jpg");
		Hardware hardware58 = new Hardware("Intel Core i5-8600K Coffee Lake", 260, new Date(System.currentTimeMillis()),
				"");
		hardware58.setCategory(category302);
		hardware58.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware58.jpg");
		Hardware hardware59 = new Hardware("AMD RYZEN 5 2600X 6-Core", 220, new Date(System.currentTimeMillis()), "");
		hardware59.setCategory(category302);
		hardware59.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware59.jpg");
		Hardware hardware60 = new Hardware("Intel Core i7-8700 Coffee Lake", 305, new Date(System.currentTimeMillis()),
				"");
		hardware60.setCategory(category302);
		hardware60.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware60.jpg");
		Hardware hardware61 = new Hardware("G.SKILL TridentZ RGB-Z Series", 140, new Date(System.currentTimeMillis()),
				"");
		hardware61.setCategory(category303);
		hardware61.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware61.jpg");
		Hardware hardware62 = new Hardware("G.SKILL TridentZ RGB Series", 125, new Date(System.currentTimeMillis()),
				"");
		hardware62.setCategory(category303);
		hardware62.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware62.jpg");
		Hardware hardware63 = new Hardware("CORSAIR Vengeance RGB Pro 16GB", 140, new Date(System.currentTimeMillis()),
				"");
		hardware63.setCategory(category303);
		hardware63.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware63.jpg");
		Hardware hardware64 = new Hardware("G.SKILL Flare X Series 16GB", 205, new Date(System.currentTimeMillis()),
				"");
		hardware64.setCategory(category303);
		hardware64.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware64.jpg");
		Hardware hardware65 = new Hardware("CORSAIR Vengeance RGB4", 145, new Date(System.currentTimeMillis()), "");
		hardware65.setCategory(category303);
		hardware65.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware65.jpg");
		Hardware hardware66 = new Hardware("Seagate BarraCuda ST1000DM010", 45, new Date(System.currentTimeMillis()),
				"");
		hardware66.setCategory(category304);
		hardware66.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware66.jpg");
		Hardware hardware67 = new Hardware("Toshiba P300 3TB Desktop PC", 85, new Date(System.currentTimeMillis()), "");
		hardware67.setCategory(category304);
		hardware67.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware67.jpg");
		Hardware hardware68 = new Hardware("Seagate BarraCuda ST3000DM008", 85, new Date(System.currentTimeMillis()),
				"");
		hardware68.setCategory(category304);
		hardware68.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware68.jpg");
		Hardware hardware69 = new Hardware("Toshiba X300 6TB Performance & Gaming", 165,
				new Date(System.currentTimeMillis()), "");
		hardware69.setCategory(category304);
		hardware69.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware69.jpg");
		Hardware hardware70 = new Hardware("WD Red 8TB NAS Hard Disk Drive", 250, new Date(System.currentTimeMillis()),
				"");
		hardware70.setCategory(category304);
		hardware70.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware70.jpg");
		Hardware hardware71 = new Hardware("CORSAIR RMx Series RM750x", 100, new Date(System.currentTimeMillis()), "");
		hardware71.setCategory(category305);
		hardware71.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware71.jpg");
		Hardware hardware72 = new Hardware("EVGA SuperNOVA 850 G3", 100, new Date(System.currentTimeMillis()), "");
		hardware72.setCategory(category305);
		hardware72.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware72.jpg");
		Hardware hardware73 = new Hardware("EVGA 450 BT 100-BT-0450-K1", 30, new Date(System.currentTimeMillis()), "");
		hardware73.setCategory(category305);
		hardware73.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware73.jpg");
		Hardware hardware74 = new Hardware("CORSAIR RMx Series RM850x CP-9020180-NA", 110,
				new Date(System.currentTimeMillis()), "");
		hardware74.setCategory(category305);
		hardware74.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware74.jpg");
		Hardware hardware75 = new Hardware("EVGA 500 BR 100-BR-0500-K1", 40, new Date(System.currentTimeMillis()), "");
		hardware75.setCategory(category305);
		hardware75.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware75.jpg");
		Hardware hardware76 = new Hardware("GIGABYTE GeForce GTX 1070", 335, new Date(System.currentTimeMillis()), "");
		hardware76.setCategory(category306);
		hardware76.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware76.jpg");
		Hardware hardware77 = new Hardware("ZOTAC GAMING GeForce RTX 2080", 1330, new Date(System.currentTimeMillis()),
				"");
		hardware77.setCategory(category306);
		hardware77.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware77.jpg");
		Hardware hardware78 = new Hardware("Sapphire Radeon NITRO+ RX 590", 260, new Date(System.currentTimeMillis()),
				"");
		hardware78.setCategory(category306);
		hardware78.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware78.jpg");
		Hardware hardware79 = new Hardware("MSI GeForce GTX 1070 Ti DirectX 12 GTX 1070", 450,
				new Date(System.currentTimeMillis()), "");
		hardware79.setCategory(category306);
		hardware79.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware79.jpg");
		Hardware hardware80 = new Hardware("PowerColor Radeon RX VEGA 64", 520, new Date(System.currentTimeMillis()),
				"");
		hardware80.setCategory(category306);
		hardware80.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware80.jpg");
		Hardware hardware81 = new Hardware("Creative Sound Blaster Audigy2 ZS SB0350 5.1 Channels 24-bit 192KHz", 43,
				new Date(System.currentTimeMillis()), "");
		hardware81.setCategory(category307);
		hardware81.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware81.jpg");
		Hardware hardware82 = new Hardware("Creative Sound Blaster Z PCIe 116dB SNR", 95,
				new Date(System.currentTimeMillis()), "");
		hardware82.setCategory(category307);
		hardware82.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware82.jpg");
		Hardware hardware83 = new Hardware("Creative Sound BlasterX AE-5 RGB 5.1 Discrete / 7.1", 145,
				new Date(System.currentTimeMillis()), "");
		hardware83.setCategory(category307);
		hardware83.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware83.jpg");
		Hardware hardware84 = new Hardware("Creative Sound BlasterX G6 Hi-Res", 145,
				new Date(System.currentTimeMillis()), "");
		hardware84.setCategory(category307);
		hardware84.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware84.jpg");
		Hardware hardware85 = new Hardware("ASUS XONAR DGX 5.1 Channels PCI Express x1", 40,
				new Date(System.currentTimeMillis()), "");
		hardware85.setCategory(category307);
		hardware85.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware85.jpg");
		Hardware hardware86 = new Hardware("Intel EXPI9301CTBLK 10/100/1000Mbps", 32,
				new Date(System.currentTimeMillis()), "");
		hardware86.setCategory(category308);
		hardware86.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware86.jpg");
		Hardware hardware87 = new Hardware("StarTech ST100SLP 1 Port Low Profile PCI / PCI-X 10/100", 12,
				new Date(System.currentTimeMillis()), "");
		hardware87.setCategory(category308);
		hardware87.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware87.jpg");
		Hardware hardware88 = new Hardware("StarTech USB433ACD1X1 USB Wi-Fi Adapter - AC600r", 24,
				new Date(System.currentTimeMillis()), "");
		hardware88.setCategory(category308);
		hardware88.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware88.jpg");
		Hardware hardware89 = new Hardware("Intel EXPI9301CT 10/100/1000Mbps", 35, new Date(System.currentTimeMillis()),
				"");
		hardware89.setCategory(category308);
		hardware89.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware89.jpg");
		Hardware hardware90 = new Hardware("TP-LINK Archer T9E PCI Express AC1900", 70,
				new Date(System.currentTimeMillis()), "");
		hardware90.setCategory(category308);
		hardware90.setImageUrl("../../../../assets/images/items/products/components/hardwares/hardware90.jpg");

		// ***** Computer ASUS 2000 ***** //
		RecipeLine recipeLine11 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.MOTHERBOARD, 1));
		recipeLine11.setComponent(hardware1);
		RecipeLine recipeLine12 = (RecipeLine) lineFactory.createLine(new RecipeLineFactory(ComponentType.CPU, 1));
		recipeLine12.setComponent(hardware3);
		RecipeLine recipeLine13 = (RecipeLine) lineFactory.createLine(new RecipeLineFactory(ComponentType.RAM, 2));
		recipeLine13.setComponent(hardware5);
		RecipeLine recipeLine14 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.HARD_DRIVE, 1));
		recipeLine14.setComponent(hardware7);
		RecipeLine recipeLine15 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.POWER_SUPPLY_UNIT, 1));
		recipeLine15.setComponent(hardware8);
		RecipeLine recipeLine16 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.VIDEO_CARD, 1));
		recipeLine16.setComponent(hardware10);
		RecipeLine recipeLine17 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.SOUND_CARD, 1));
		recipeLine17.setComponent(hardware12);
		RecipeLine recipeLine18 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.NEWTWORK_CARD, 1));
		recipeLine18.setComponent(hardware13);
		RecipeLine recipeLine19 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.OPERATING_SYSTEM, 1));
		recipeLine19.setComponent(software1);

		Recipe recipe1 = new Recipe("Computer: ASUS 2000", 10, new Date(System.currentTimeMillis()), "");
		recipe1.setCategory(category1);
		recipe1.setImageUrl("../../../../assets/images/items/products/recipes/recipe1.jpg");
		recipe1.addLine(recipeLine11);
		recipe1.addLine(recipeLine12);
		recipe1.addLine(recipeLine13);
		recipe1.addLine(recipeLine14);
		recipe1.addLine(recipeLine15);
		recipe1.addLine(recipeLine16);
		recipe1.addLine(recipeLine17);
		recipe1.addLine(recipeLine18);
		recipe1.addLine(recipeLine19);

		// ***** Computer ASUS G1009 ***** //
		RecipeLine recipeLine21 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.MOTHERBOARD, 1));
		recipeLine21.setComponent(hardware1);
		RecipeLine recipeLine22 = (RecipeLine) lineFactory.createLine(new RecipeLineFactory(ComponentType.CPU, 1));
		recipeLine22.setComponent(hardware3);
		RecipeLine recipeLine23 = (RecipeLine) lineFactory.createLine(new RecipeLineFactory(ComponentType.RAM, 4));
		recipeLine23.setComponent(hardware6);
		RecipeLine recipeLine24 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.HARD_DRIVE, 1));
		recipeLine24.setComponent(hardware7);
		RecipeLine recipeLine25 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.POWER_SUPPLY_UNIT, 1));
		recipeLine25.setComponent(hardware8);
		RecipeLine recipeLine26 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.VIDEO_CARD, 1));
		recipeLine26.setComponent(hardware9);
		RecipeLine recipeLine27 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.SOUND_CARD, 1));
		recipeLine27.setComponent(hardware12);
		RecipeLine recipeLine28 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.NEWTWORK_CARD, 1));
		recipeLine28.setComponent(hardware13);
		RecipeLine recipeLine29 = (RecipeLine) lineFactory
				.createLine(new RecipeLineFactory(ComponentType.OPERATING_SYSTEM, 1));
		recipeLine29.setComponent(software3);

		Recipe recipe2 = new Recipe("Computer: ASUS LK40", 10, new Date(System.currentTimeMillis()), "");
		recipe2.setCategory(category1);
		recipe2.setImageUrl("../../../../assets/images/items/products/recipes/recipe2.jpg");
		recipe2.addLine(recipeLine21);
		recipe2.addLine(recipeLine22);
		recipe2.addLine(recipeLine23);
		recipe2.addLine(recipeLine24);
		recipe2.addLine(recipeLine25);
		recipe2.addLine(recipeLine26);
		recipe2.addLine(recipeLine27);
		recipe2.addLine(recipeLine28);
		recipe2.addLine(recipeLine29);

		// ***** Package: Computer ASUS G1009 + mouse + keyboard ***** //
		PackageLine packageLine1 = (PackageLine) lineFactory.createLine(new PackageLineFactory(1));
		packageLine1.setProduct(recipe1);
		PackageLine packageLine2 = (PackageLine) lineFactory.createLine(new PackageLineFactory(1));
		packageLine2.setProduct(hardware16);
		PackageLine packageLine3 = (PackageLine) lineFactory.createLine(new PackageLineFactory(1));
		packageLine3.setProduct(hardware19);

		Package package1 = new Package("Computer ASUS G1009 + Mouse: Corssair 2018 + Keyboard: Logitech K800", 4,
				new Date(System.currentTimeMillis()), "");
		package1.setCategory(category5);
		package1.setImageUrl("../../../../assets/images/items/packages/package1.jpg");
		package1.addLine(packageLine1);
		package1.addLine(packageLine2);
		package1.addLine(packageLine3);

		// ------------------------------------------------------------------- //
		// ------------------------------------------------------------------- //

		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);

		List<Item> items = new ArrayList<Item>();

		// ***** Software *****
		items.add(software1);
		items.add(software2);
		items.add(software3);
		items.add(software4);

		// ***** Hardware *****
		items.add(hardware1);
		items.add(hardware2);
		items.add(hardware3);
		items.add(hardware4);
		items.add(hardware5);
		items.add(hardware6);
		items.add(hardware7);
		items.add(hardware8);
		items.add(hardware9);
		items.add(hardware10);
		items.add(hardware11);
		items.add(hardware12);
		items.add(hardware13);

		// ***** Components *****
		items.add(hardware14);
		items.add(hardware15);
		items.add(hardware16);
		items.add(hardware17);
		items.add(hardware18);
		items.add(hardware19);

		// --------------- new items --------------------
		items.add(hardware20);
		items.add(hardware21);
		items.add(hardware22);
		items.add(hardware23);
		items.add(hardware24);
		items.add(hardware25);
		items.add(hardware26);
		items.add(hardware27);
		items.add(hardware28);
		items.add(hardware29);
		items.add(hardware30);
		items.add(hardware31);
		items.add(hardware32);
		items.add(hardware33);
		items.add(hardware34);
		items.add(hardware35);
		items.add(hardware36);
		items.add(hardware37);
		items.add(hardware38);
		items.add(hardware39);
		items.add(hardware40);
		items.add(hardware41);
		items.add(hardware42);
		items.add(hardware43);
		items.add(hardware44);
		items.add(hardware45);
		items.add(hardware46);
		items.add(hardware47);
		items.add(hardware48);
		items.add(hardware49);
		items.add(hardware50);

		// ------------ newer items --------------
		items.add(hardware51);
		items.add(hardware52);
		items.add(hardware53);
		items.add(hardware54);
		items.add(hardware55);
		items.add(hardware56);
		items.add(hardware57);
		items.add(hardware58);
		items.add(hardware59);
		items.add(hardware60);
		items.add(hardware61);
		items.add(hardware62);
		items.add(hardware63);
		items.add(hardware64);
		items.add(hardware65);
		items.add(hardware66);
		items.add(hardware67);
		items.add(hardware68);
		items.add(hardware69);
		items.add(hardware70);
		items.add(hardware71);
		items.add(hardware72);
		items.add(hardware73);
		items.add(hardware74);
		items.add(hardware75);
		items.add(hardware76);
		items.add(hardware77);
		items.add(hardware78);
		items.add(hardware79);
		items.add(hardware80);
		items.add(hardware81);
		items.add(hardware82);
		items.add(hardware83);
		items.add(hardware84);
		items.add(hardware85);
		items.add(hardware86);
		items.add(hardware87);
		items.add(hardware88);
		items.add(hardware89);
		items.add(hardware90);

		// ***** Recipes *****
		items.add(recipe1);
		items.add(recipe2);

		// ***** Packages *****
		items.add(package1);

//		long timeSpent = 0;
//		
//		List<CartLine> cartLines = null;
//		
//		for (int i = 0; i < 100; i++) {
//			long startTime = System.nanoTime();;
//			cartLines = objectGenerator.genCartLines(items, 10000);
//			long stopTime = System.nanoTime();;
//			long elapsedTime = stopTime - startTime;
//			System.out.println("TIME ELAPSED: " + elapsedTime);
//			
//			timeSpent += elapsedTime / 100;
//		}
//		
//		System.out.println("TIME ELAPSED: " + timeSpent);
//		
//		System.exit(0);

		List<CartLine> cartLines = objectGenerator.genCartLines(items, 3000);
		Object[] completeOrders = objectGenerator.genCompleteOrders(cartLines, customers);
		List<Cart> carts = (List<Cart>) completeOrders[0];
		List<Orders> orders = (List<Orders>) completeOrders[1];
		List<Payment> payments = (List<Payment>) completeOrders[2];

		// ------------------------------------------------------------------- //

		List<Double> prices = new ArrayList<Double>();

		// ***** Software *****
		prices.add(500.0);
		prices.add(420.0);
		prices.add(275.0);
		prices.add(320.0);

		// ***** Hardware *****
		prices.add(1500.0);
		prices.add(950.0);
		prices.add(620.0);
		prices.add(200.0);
		prices.add(120.0);
		prices.add(70.0);
		prices.add(700.0);
		prices.add(280.0);
		prices.add(1280.0);
		prices.add(1120.0);
		prices.add(1200.0);
		prices.add(180.0);
		prices.add(310.0);

		// ******* new prices *******
		prices.add(500.0);
		prices.add(120.0);
		prices.add(130.0);
		prices.add(250.0);
		prices.add(135.0);
		prices.add(80.0);
		prices.add(82.0);
		prices.add(152.0);
		prices.add(172.0);
		prices.add(300.0);

		prices.add(500.0);
		prices.add(330.0);
		prices.add(230.0);
		prices.add(130.0);
		prices.add(280.0);
		prices.add(390.0);
		prices.add(288.0);
		prices.add(296.0);
		prices.add(190.0);
		prices.add(600.0);

		prices.add(550.0);
		prices.add(452.0);
		prices.add(800.0);
		prices.add(300.0);
		prices.add(500.0);
		prices.add(830.0);
		prices.add(330.0);
		prices.add(630.0);
		prices.add(230.0);
		prices.add(430.0);
		prices.add(520.0);

		// --------- newer prices ---------
		prices.add(750.0);
		prices.add(490.0);
		prices.add(900.0);
		prices.add(640.0);
		prices.add(660.0);
		prices.add(340.0);
		prices.add(400.0);
		prices.add(600.0);
		prices.add(890.0);
		prices.add(340.0);
		prices.add(200.0);
		prices.add(640.0);
		prices.add(643.0);
		prices.add(432.0);
		prices.add(234.0);
		prices.add(800.0);
		prices.add(1250.0);
		prices.add(750.0);
		prices.add(850.0);
		prices.add(962.0);
		prices.add(800.0);
		prices.add(900.0);
		prices.add(1020.0);
		prices.add(1235.0);
		prices.add(952.0);
		prices.add(750.0);
		prices.add(825.0);
		prices.add(824.0);
		prices.add(500.0);
		prices.add(520.0);
		prices.add(722.0);
		prices.add(420.0);
		prices.add(450.0);
		prices.add(290.0);
		prices.add(345.0);
		prices.add(231.0);
		prices.add(200.0);
		prices.add(195.0);
		prices.add(500.0);
		prices.add(660.0);

		// ***** Components *****
		prices.add(260.0);
		prices.add(210.0);
		prices.add(200.0);
		prices.add(120.0);
		prices.add(100.0);
		prices.add(120.0);

		// ***** Recipes *****
		prices.add(7400.0);
		prices.add(5200.0);

		// ***** Packages *****
		prices.add(7500.0);

		List<CatalogueItem> catalogueLines1 = objectGenerator.genCatalogueLines(items, prices);
		Catalogue catalogue1 = objectGenerator.genCatalogue(catalogueLines1, 2, 2018);

		List<CatalogueItem> catalogueLines2 = objectGenerator.genCatalogueLines(items, prices);
		Catalogue catalogue2 = objectGenerator.genCatalogue(catalogueLines2, 3, 2018);

		List<CatalogueItem> catalogueLines3 = objectGenerator.genCatalogueLines(items, prices);
		Catalogue catalogue3 = objectGenerator.genCatalogue(catalogueLines3, 4, 2018);

		List<CatalogueItem> catalogueLines4 = objectGenerator.genCatalogueLines(items, prices);
		Catalogue catalogue4 = objectGenerator.genCatalogue(catalogueLines4, 5, 2018);

		List<CatalogueItem> catalogueLines5 = objectGenerator.genCatalogueLines(items, prices);
		Catalogue catalogue5 = objectGenerator.genCatalogue(catalogueLines5, 6, 2018);

		List<CatalogueItem> catalogueLines6 = objectGenerator.genCatalogueLines(items, prices);
		Catalogue catalogue6 = objectGenerator.genCatalogue(catalogueLines6, 7, 2018);

		List<CatalogueItem> catalogueLines7 = objectGenerator.genCatalogueLines(items, prices);
		Catalogue catalogue7 = objectGenerator.genCatalogue(catalogueLines7, 8, 2018);

		List<CatalogueItem> catalogueLines8 = objectGenerator.genCatalogueLines(items, prices);
		Catalogue catalogue8 = objectGenerator.genCatalogue(catalogueLines8, 9, 2018);

		List<CatalogueItem> catalogueLines9 = objectGenerator.genCatalogueLines(items, prices);
		Catalogue catalogue9 = objectGenerator.genCatalogue(catalogueLines9, 10, 2018);

		List<CatalogueItem> catalogueLines10 = objectGenerator.genCatalogueLines(items, prices);
		Catalogue catalogue10 = objectGenerator.genCatalogue(catalogueLines10, 11, 2018);

		List<CatalogueItem> catalogueLines11 = objectGenerator.genCatalogueLines(items, prices);
		Catalogue catalogue11 = objectGenerator.genCatalogue(catalogueLines11, 12, 2018);

		List<CatalogueItem> catalogueLines12 = objectGenerator.genCatalogueLines(items, prices);
		Catalogue catalogue12 = objectGenerator.genCatalogue(catalogueLines12, 1, 2019);

		// ------------------------------------------------------------------- //
		// ------------------------------------------------------------------- //
		// ------------------------------------------------------------------- //

		countyService.insertCounty(county);

		cityService.insertCity(city1);
		cityService.insertCity(city2);
		cityService.insertCity(city3);

		addressService.insertAddress(address1);
		addressService.insertAddress(address2);
		addressService.insertAddress(address3);
		addressService.insertAddress(address4);
		addressService.insertAddress(address5);
		addressService.insertAddress(address6);
		addressService.insertAddress(address7);
		addressService.insertAddress(address8);

		subscriptionService.insertSubscription(subscription1);
		subscriptionService.insertSubscription(subscription2);
		subscriptionService.insertSubscription(subscription3);

		accountService.insertAccount(customer1);
		accountService.insertAccount(customer2);
		accountService.insertAccount(customer3);

		// ------------------------------------------------------------------- //

		accountService.insertAccount(employee1);
		accountService.insertAccount(employee2);

		// ------------------------------------------------------------------- //

		categoryService.insertCategory(category1);
		categoryService.insertCategory(category2);

		categoryService.insertCategory(category3);
		categoryService.insertCategory(category301);
		categoryService.insertCategory(category302);
		categoryService.insertCategory(category303);
		categoryService.insertCategory(category304);
		categoryService.insertCategory(category305);
		categoryService.insertCategory(category306);
		categoryService.insertCategory(category307);
		categoryService.insertCategory(category308);

		categoryService.insertCategory(category4);
		categoryService.insertCategory(category401);
		categoryService.insertCategory(category402);
		categoryService.insertCategory(category403);

		categoryService.insertCategory(category5);

		// ------------------------------------------------------------------- //

		itemService.insertItem(software1);
		itemService.insertItem(software2);
		itemService.insertItem(software3);
		itemService.insertItem(software4);

		itemService.insertItem(hardware1);
		itemService.insertItem(hardware2);
		itemService.insertItem(hardware3);
		itemService.insertItem(hardware4);
		itemService.insertItem(hardware5);
		itemService.insertItem(hardware6);
		itemService.insertItem(hardware7);
		itemService.insertItem(hardware8);
		itemService.insertItem(hardware9);
		itemService.insertItem(hardware10);
		itemService.insertItem(hardware11);
		itemService.insertItem(hardware12);
		itemService.insertItem(hardware13);
		itemService.insertItem(hardware14);
		itemService.insertItem(hardware15);
		itemService.insertItem(hardware16);
		itemService.insertItem(hardware17);
		itemService.insertItem(hardware18);
		itemService.insertItem(hardware19);

		// ----------- products to persist -------------------
		itemService.insertItem(hardware20);
		itemService.insertItem(hardware21);
		itemService.insertItem(hardware22);
		itemService.insertItem(hardware23);
		itemService.insertItem(hardware24);
		itemService.insertItem(hardware25);
		itemService.insertItem(hardware26);
		itemService.insertItem(hardware27);
		itemService.insertItem(hardware28);
		itemService.insertItem(hardware29);
		itemService.insertItem(hardware30);
		itemService.insertItem(hardware31);
		itemService.insertItem(hardware32);
		itemService.insertItem(hardware33);
		itemService.insertItem(hardware34);
		itemService.insertItem(hardware35);
		itemService.insertItem(hardware36);
		itemService.insertItem(hardware37);
		itemService.insertItem(hardware38);
		itemService.insertItem(hardware39);
		itemService.insertItem(hardware40);
		itemService.insertItem(hardware41);
		itemService.insertItem(hardware42);
		itemService.insertItem(hardware43);
		itemService.insertItem(hardware44);
		itemService.insertItem(hardware45);
		itemService.insertItem(hardware46);
		itemService.insertItem(hardware47);
		itemService.insertItem(hardware48);
		itemService.insertItem(hardware49);
		itemService.insertItem(hardware50);

		itemService.insertItem(hardware51);
		itemService.insertItem(hardware52);
		itemService.insertItem(hardware53);
		itemService.insertItem(hardware54);
		itemService.insertItem(hardware55);
		itemService.insertItem(hardware56);
		itemService.insertItem(hardware57);
		itemService.insertItem(hardware58);
		itemService.insertItem(hardware59);
		itemService.insertItem(hardware60);
		itemService.insertItem(hardware61);
		itemService.insertItem(hardware62);
		itemService.insertItem(hardware63);
		itemService.insertItem(hardware64);
		itemService.insertItem(hardware65);
		itemService.insertItem(hardware66);
		itemService.insertItem(hardware67);
		itemService.insertItem(hardware68);
		itemService.insertItem(hardware69);
		itemService.insertItem(hardware70);
		itemService.insertItem(hardware71);
		itemService.insertItem(hardware72);
		itemService.insertItem(hardware73);
		itemService.insertItem(hardware74);
		itemService.insertItem(hardware75);
		itemService.insertItem(hardware76);
		itemService.insertItem(hardware77);
		itemService.insertItem(hardware78);
		itemService.insertItem(hardware79);
		itemService.insertItem(hardware80);
		itemService.insertItem(hardware81);
		itemService.insertItem(hardware82);
		itemService.insertItem(hardware83);
		itemService.insertItem(hardware84);
		itemService.insertItem(hardware85);
		itemService.insertItem(hardware86);
		itemService.insertItem(hardware87);
		itemService.insertItem(hardware88);
		itemService.insertItem(hardware89);
		itemService.insertItem(hardware90);

		itemService.insertItem(recipe1);

		recipeLineService.insertRecipeLine(recipeLine11);
		recipeLineService.insertRecipeLine(recipeLine12);
		recipeLineService.insertRecipeLine(recipeLine13);
		recipeLineService.insertRecipeLine(recipeLine14);
		recipeLineService.insertRecipeLine(recipeLine15);
		recipeLineService.insertRecipeLine(recipeLine16);
		recipeLineService.insertRecipeLine(recipeLine17);
		recipeLineService.insertRecipeLine(recipeLine18);
		recipeLineService.insertRecipeLine(recipeLine19);

		itemService.insertItem(recipe2);

		recipeLineService.insertRecipeLine(recipeLine21);
		recipeLineService.insertRecipeLine(recipeLine22);
		recipeLineService.insertRecipeLine(recipeLine23);
		recipeLineService.insertRecipeLine(recipeLine24);
		recipeLineService.insertRecipeLine(recipeLine25);
		recipeLineService.insertRecipeLine(recipeLine26);
		recipeLineService.insertRecipeLine(recipeLine27);
		recipeLineService.insertRecipeLine(recipeLine28);
		recipeLineService.insertRecipeLine(recipeLine29);

		itemService.insertItem(package1);

		packageLineService.insertPackageLine(packageLine1);
		packageLineService.insertPackageLine(packageLine2);
		packageLineService.insertPackageLine(packageLine3);

		for (Cart cart : carts) {
			cartService.insertCart(cart);
		}

		for (CartLine cartLine : cartLines) {
			cartLineService.insertCartLine(cartLine);
		}

		for (Orders order : orders) {
			orderService.insertOrder(order);
		}

		for (Payment payment : payments) {
			paymentService.insertPayment(payment);
		}

		catalogueService.insertCatalogue(catalogue1);
		for (CatalogueItem catalogueLine : catalogueLines1) {
			catalogueItemService.insertCatalogueItem(catalogueLine);
		}

		catalogueService.insertCatalogue(catalogue2);
		for (CatalogueItem catalogueLine : catalogueLines2) {
			catalogueItemService.insertCatalogueItem(catalogueLine);
		}

		catalogueService.insertCatalogue(catalogue3);
		for (CatalogueItem catalogueLine : catalogueLines3) {
			catalogueItemService.insertCatalogueItem(catalogueLine);
		}

		catalogueService.insertCatalogue(catalogue4);
		for (CatalogueItem catalogueLine : catalogueLines4) {
			catalogueItemService.insertCatalogueItem(catalogueLine);
		}

		catalogueService.insertCatalogue(catalogue5);
		for (CatalogueItem catalogueLine : catalogueLines5) {
			catalogueItemService.insertCatalogueItem(catalogueLine);
		}

		catalogueService.insertCatalogue(catalogue6);
		for (CatalogueItem catalogueLine : catalogueLines6) {
			catalogueItemService.insertCatalogueItem(catalogueLine);
		}

		catalogueService.insertCatalogue(catalogue7);
		for (CatalogueItem catalogueLine : catalogueLines7) {
			catalogueItemService.insertCatalogueItem(catalogueLine);
		}

		catalogueService.insertCatalogue(catalogue8);
		for (CatalogueItem catalogueLine : catalogueLines8) {
			catalogueItemService.insertCatalogueItem(catalogueLine);
		}

		catalogueService.insertCatalogue(catalogue9);
		for (CatalogueItem catalogueLine : catalogueLines9) {
			catalogueItemService.insertCatalogueItem(catalogueLine);
		}

		catalogueService.insertCatalogue(catalogue10);
		for (CatalogueItem catalogueLine : catalogueLines10) {
			catalogueItemService.insertCatalogueItem(catalogueLine);
		}

		catalogueService.insertCatalogue(catalogue11);
		for (CatalogueItem catalogueLine : catalogueLines11) {
			catalogueItemService.insertCatalogueItem(catalogueLine);
		}

		catalogueService.insertCatalogue(catalogue12);
		for (CatalogueItem catalogueLine : catalogueLines12) {
			catalogueItemService.insertCatalogueItem(catalogueLine);
		}

		displayData.printInfo("Data successfully loaded.");
	}

	private Boolean isDBEmpty() {
		Boolean isEmpty = false;
		List<County> counties = countyService.findAllCounties();

		if (counties.isEmpty()) {
			displayData.printInfo("The database is empty.");
			isEmpty = true;
		} else {
			displayData.printInfo("The database in NOT empty. No data loading required.");
		}

		return isEmpty;
	}
}
