package com.project.sms.utils;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.project.sms.entities.account.IAccountService;
import com.project.sms.entities.category.Category;
import com.project.sms.entities.category.ICategoryService;
import com.project.sms.entities.customer.Customer;
import com.project.sms.entities.item.Hardware;
import com.project.sms.entities.item.IItemService;
import com.project.sms.entities.item.Software;
import com.project.sms.entities.location.Address;
import com.project.sms.entities.location.City;
import com.project.sms.entities.location.County;
import com.project.sms.entities.location.IAddressService;
import com.project.sms.entities.location.ICityService;
import com.project.sms.entities.location.ICountyService;
import com.project.sms.entities.order.IPaymentService;
import com.project.sms.entities.order.Payment;
import com.project.sms.entities.pack.IPackageLineService;
import com.project.sms.entities.pack.Package;
import com.project.sms.entities.pack.PackageLine;
import com.project.sms.entities.recipe.IRecipeLineService;
import com.project.sms.entities.recipe.Recipe;
import com.project.sms.entities.recipe.RecipeLine;
import com.project.sms.entities.subscription.ISubscriptionService;
import com.project.sms.entities.subscription.Subscription;
import com.project.sms.enums.AccountStatus;
import com.project.sms.enums.PaymentStatus;
import com.project.sms.enums.PaymentType;
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
	private IPaymentService paymentService;
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
	private DisplayData displayData;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (isDBEmpty()) {
			loadData();
		}
	}

	private void loadData() {
		displayData.printInfo("Starting data loading...");

		County county = new County("Iasi", "Moldova");

		City city1 = new City("Iasi");
		city1.setCounty(county);
		City city2 = new City("Pascani");
		city2.setCounty(county);
		City city3 = new City("Targu Frumos");
		city3.setCounty(county);

		Address address1 = new Address("Alexandru cel Bun", 51, 270123);
		address1.setCity(city1);
		Address address2 = new Address("Marinelor", 11, 280129);
		address2.setCity(city2);
		Address address3 = new Address("Marinelor", 27, 280129);
		address3.setCity(city2);
		Address address4 = new Address("Marin Cordoba", 100, 290111);
		address4.setCity(city3);
		Address address5 = new Address("Cosmopolitan", 127, 270123);
		address5.setCity(city1);
		Address address6 = new Address("Cosmopolitan", 129, 270123);
		address6.setCity(city1);

		Subscription subscription1 = new Subscription(SubscriptionType.DISCOUNTS);
		Subscription subscription2 = new Subscription(SubscriptionType.NEW_PRODUCTS);
		Subscription subscription3 = new Subscription(SubscriptionType.PROMOTIONAL);

		Customer customer1 = new Customer("Alex", "alex123", "Alexandru", "alex_cozma@gmail.com", "0748974419",
				new Date(System.currentTimeMillis()), AccountStatus.ACTIVE);
		customer1.setAddress(address1);
		customer1.getDeliveryAddresses().add(address5);
		customer1.getDeliveryAddresses().add(address6);
		customer1.getSubscriptions().add(subscription1);
		customer1.getSubscriptions().add(subscription3);
		Customer customer2 = new Customer("Buzzy23", "password", "Maxim", "max_96@yahoo.com", "0742114411",
				new Date(System.currentTimeMillis()), AccountStatus.ACTIVE);
		customer2.setAddress(address2);
		Customer customer3 = new Customer("Alinacika", "saalina", "Alina", "alina_sandra@gmail.com", "0742271410",
				new Date(System.currentTimeMillis()), AccountStatus.ACTIVE);
		customer3.setAddress(address3);
		customer3.getDeliveryAddresses().add(address4);
		customer3.getSubscriptions().add(subscription1);
		customer3.getSubscriptions().add(subscription2);
		customer3.getSubscriptions().add(subscription3);

		// ------------------------------------------------------------------- //

		Payment payment1 = new Payment(PaymentType.MAESTRO, PaymentStatus.APPROVED, null);
		Payment payment2 = new Payment(PaymentType.VISA, PaymentStatus.APPROVED, null);
		Payment payment3 = new Payment(PaymentType.MAESTRO, PaymentStatus.COMPLETED, null);

		// ------------------------------------------------------------------- //
		
		Category category1 = new Category("Laptops", new Date(System.currentTimeMillis()));
		Category category2 = new Category("Software", new Date(System.currentTimeMillis()));
		
		Category category3 = new Category("Components", new Date(System.currentTimeMillis()));
		Category category301 = new Category("Motherboards", new Date(System.currentTimeMillis()));
		category301.setParentCategory(category3);
		Category category302 = new Category("CPUs", new Date(System.currentTimeMillis()));
		category302.setParentCategory(category3);
		Category category303 = new Category("RAMs", new Date(System.currentTimeMillis()));
		category303.setParentCategory(category3);
		Category category304 = new Category("Hard drives", new Date(System.currentTimeMillis()));
		category304.setParentCategory(category3);
		Category category305 = new Category("Power supply units", new Date(System.currentTimeMillis()));
		category305.setParentCategory(category3);
		Category category306 = new Category("Video cards", new Date(System.currentTimeMillis()));
		category306.setParentCategory(category3);
		Category category307 = new Category("Sound cards", new Date(System.currentTimeMillis()));
		category307.setParentCategory(category3);
		Category category308 = new Category("Network cards", new Date(System.currentTimeMillis()));
		category308.setParentCategory(category3);
		
		Category category4 = new Category("Accessories", new Date(System.currentTimeMillis()));
		Category category401 = new Category("Headphones", new Date(System.currentTimeMillis()));
		category401.setParentCategory(category4);
		Category category402 = new Category("Mouses", new Date(System.currentTimeMillis()));
		category402.setParentCategory(category4);
		Category category403 = new Category("Keyboards", new Date(System.currentTimeMillis()));
		category403.setParentCategory(category4);
		
		Category category5 = new Category("Packages", new Date(System.currentTimeMillis()));
		
		// ------------------------------------------------------------------- //

		Software software1 = new Software("Windows 10 Professional", 20, new Date(System.currentTimeMillis()), "", "");
		software1.setCategory(category2);
		Software software2 = new Software("Windows 10 Home", 20, new Date(System.currentTimeMillis()), "", "");
		software2.setCategory(category2);
		Software software3 = new Software("Ubuntu", 15, new Date(System.currentTimeMillis()), "", "");
		software3.setCategory(category2);
		Software software4 = new Software("FreeDos", 100, new Date(System.currentTimeMillis()), "", "");
		software4.setCategory(category2);

		Hardware hardware1 = new Hardware("Motherboard: M.2 B250 mATX", 12, new Date(System.currentTimeMillis()), "");
		hardware1.setCategory(category301);
		Hardware hardware2 = new Hardware("CPU: Turbo Unlocked LGA1151", 16, new Date(System.currentTimeMillis()), "");
		hardware2.setCategory(category302);
		Hardware hardware3 = new Hardware("CPU: Turbo Unlocked LGA2000", 9, new Date(System.currentTimeMillis()), "");
		hardware3.setCategory(category302);
		Hardware hardware4 = new Hardware("RAM 16GB", 10, new Date(System.currentTimeMillis()), "");
		hardware4.setCategory(category303);
		Hardware hardware5 = new Hardware("RAM 8GB", 43, new Date(System.currentTimeMillis()), "");
		hardware5.setCategory(category303);
		Hardware hardware6 = new Hardware("RAM 4GB", 32, new Date(System.currentTimeMillis()), "");
		hardware6.setCategory(category303);
		Hardware hardware7 = new Hardware("Hard drive: Seagate SkyHawk", 32, new Date(System.currentTimeMillis()), "");
		hardware7.setCategory(category304);
		Hardware hardware8 = new Hardware("Power supply unit: Supermicro MBE-314E-416", 15,
				new Date(System.currentTimeMillis()), "");
		hardware8.setCategory(category305);
		Hardware hardware9 = new Hardware("Video card: MSI Gaming GeForce GT 710", 12,
				new Date(System.currentTimeMillis()), "");
		hardware9.setCategory(category306);
		Hardware hardware10 = new Hardware("Video card: Gigabyte Geforce GTX 1050", 31,
				new Date(System.currentTimeMillis()), "");
		hardware10.setCategory(category306);
		Hardware hardware11 = new Hardware("Video card: EVGA GeForce GTX 1060", 17,
				new Date(System.currentTimeMillis()), "");
		hardware11.setCategory(category306);
		Hardware hardware12 = new Hardware("Sound card: Intel G3260 3MB Haswell Dual-Core 3.3 GHz", 19,
				new Date(System.currentTimeMillis()), "");
		hardware12.setCategory(category307);
		Hardware hardware13 = new Hardware("Network card: Thecus C10GTR 10GbE Network Interface Card", 12,
				new Date(System.currentTimeMillis()), "");
		hardware13.setCategory(category308);

		Hardware hardware14 = new Hardware("Headphones: Skull Candy", 40, new Date(System.currentTimeMillis()), "");
		hardware14.setCategory(category401);
		Hardware hardware15 = new Hardware("Headphones: Beats", 20, new Date(System.currentTimeMillis()), "");
		hardware15.setCategory(category401);
		Hardware hardware16 = new Hardware("Mouse: Corssair 2018", 13, new Date(System.currentTimeMillis()), "");
		hardware16.setCategory(category402);
		Hardware hardware17 = new Hardware("Mouse: Corssair K20", 33, new Date(System.currentTimeMillis()), "");
		hardware17.setCategory(category402);
		Hardware hardware18 = new Hardware("Mouse: Corssair Z92", 12, new Date(System.currentTimeMillis()), "");
		hardware18.setCategory(category402);
		Hardware hardware19 = new Hardware("Keyboard: Logitech K800", 51, new Date(System.currentTimeMillis()), "");
		hardware19.setCategory(category403);

		// ***** Computer ASUS 2000 ***** //
		RecipeLine recipeLine11 = new RecipeLine(1);
		recipeLine11.setComponent(hardware1);
		RecipeLine recipeLine12 = new RecipeLine(1);
		recipeLine12.setComponent(hardware3);
		RecipeLine recipeLine13 = new RecipeLine(2);
		recipeLine13.setComponent(hardware5);
		RecipeLine recipeLine14 = new RecipeLine(1);
		recipeLine14.setComponent(hardware7);
		RecipeLine recipeLine15 = new RecipeLine(1);
		recipeLine15.setComponent(hardware8);
		RecipeLine recipeLine16 = new RecipeLine(1);
		recipeLine16.setComponent(hardware10);
		RecipeLine recipeLine17 = new RecipeLine(1);
		recipeLine17.setComponent(hardware12);
		RecipeLine recipeLine18 = new RecipeLine(1);
		recipeLine18.setComponent(hardware13);
		RecipeLine recipeLine19 = new RecipeLine(1);
		recipeLine19.setComponent(software1);

		Recipe recipe1 = new Recipe("Computer: ASUS 2000", 10, new Date(System.currentTimeMillis()), "");
		recipe1.setCategory(category1);
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
		RecipeLine recipeLine21 = new RecipeLine(1);
		recipeLine21.setComponent(hardware1);
		RecipeLine recipeLine22 = new RecipeLine(1);
		recipeLine22.setComponent(hardware3);
		RecipeLine recipeLine23 = new RecipeLine(4);
		recipeLine23.setComponent(hardware6);
		RecipeLine recipeLine24 = new RecipeLine(1);
		recipeLine24.setComponent(hardware7);
		RecipeLine recipeLine25 = new RecipeLine(1);
		recipeLine25.setComponent(hardware8);
		RecipeLine recipeLine26 = new RecipeLine(1);
		recipeLine26.setComponent(hardware9);
		RecipeLine recipeLine27 = new RecipeLine(1);
		recipeLine27.setComponent(hardware12);
		RecipeLine recipeLine28 = new RecipeLine(1);
		recipeLine28.setComponent(hardware13);
		RecipeLine recipeLine29 = new RecipeLine(1);
		recipeLine29.setComponent(software3);

		Recipe recipe2 = new Recipe("Computer: ASUS LK40", 10, new Date(System.currentTimeMillis()), "");
		recipe2.setCategory(category1);
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
		PackageLine packageLine1 = new PackageLine(1);
		packageLine1.setProduct(recipe1);
		PackageLine packageLine2 = new PackageLine(1);
		packageLine2.setProduct(hardware16);
		PackageLine packageLine3 = new PackageLine(1);
		packageLine3.setProduct(hardware19);

		Package package1 = new Package("Computer ASUS G1009 + Mouse: Corssair 2018 + Keyboard: Logitech K800", 4,
				new Date(System.currentTimeMillis()), "");
		package1.setCategory(category5);
		package1.addLine(packageLine1);
		package1.addLine(packageLine2);
		package1.addLine(packageLine3);

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

		subscriptionService.insertSubscription(subscription1);
		subscriptionService.insertSubscription(subscription2);
		subscriptionService.insertSubscription(subscription3);

		accountService.insertAccount(customer1);
		accountService.insertAccount(customer2);
		accountService.insertAccount(customer3);

		// ------------------------------------------------------------------- //

		paymentService.insertPayment(payment1);
		paymentService.insertPayment(payment2);
		paymentService.insertPayment(payment3);

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
