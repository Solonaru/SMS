package com.project.sms.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.project.sms.entities.lines.ILine;
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
import com.project.sms.enums.Month;
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

		System.out.println("------------------------------------------------------");

		Item item = itemService.findItemById(4).get();
		System.out.println("Is the item listed: " + item.isListed());
		System.out.println("The price of the item is: " + item.getPrice(Month.DECEMBER));

		Employee employee = (Employee) accountService.findAccountById(400000005).get();
		System.out.println("EMPLOYEE CAN CREATE CATEGORY: " + employee.getRightType().canCreateCategory());
		System.out.println("EMPLOYEE HAS CREATE PRODUCT: " + employee.getRightType().canCreateProduct());

		System.out.println("------------------------------------------------------");

		Package package1 = (Package) itemService.findItemById(26).get();
		Iterator<? extends ILine> iterator1 = package1.createIterator();

		while (iterator1.hasNext()) {
			PackageLine packageLine = (PackageLine) iterator1.next();
			System.out.println("PACKAGE LINE: " + packageLine.getProduct().getName());
		}

		System.out.println("------------------------------------------------------");

		Recipe recipe1 = (Recipe) itemService.findItemById(24).get();
		Iterator<? extends ILine> iterator2 = recipe1.createIterator();

		while (iterator2.hasNext()) {
			RecipeLine recipeLine = (RecipeLine) iterator2.next();
			System.out.println("RECIPE LINE: " + recipeLine.getComponent().getName());
		}

		System.out.println("------------------------------------------------------");

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

		List<CartLine> cartLines = objectGenerator.genCartLines(items, 100);
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
