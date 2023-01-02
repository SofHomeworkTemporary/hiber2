package sof;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import sof.dao.*;
import sof.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Main {
    private final SessionFactory sessionFactory;
    private final ActorDao actorDao;
    private final AddressDao addressDao;
    private final CategoryDao categoryDao;
    private final CityDao cityDao;
    private final CountryDao countryDao;
    private final CustomerDao customerDao;
    private final FilmDao filmDao;
    private final FilmTextDao filmTextDao;
    private final InventoryDao inventoryDao;
    private final LanguageDao languageDao;
    private final PaymentDao paymentDao;
    private final RentalDao rentalDao;
    private final StaffDao staffDao;
    private final StoreDao storeDao;

    public Main() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movie?serverTimezone=UTC");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "1234");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        properties.put(Environment.FORMAT_SQL, "true");
        properties.put(Environment.SHOW_SQL, "true");

        sessionFactory = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .addProperties(properties)
                .buildSessionFactory();

        actorDao = new ActorDao(sessionFactory);
        addressDao = new AddressDao(sessionFactory);
        categoryDao = new CategoryDao(sessionFactory);
        cityDao = new CityDao(sessionFactory);
        countryDao = new CountryDao(sessionFactory);
        customerDao = new CustomerDao(sessionFactory);
        filmDao = new FilmDao(sessionFactory);
        filmTextDao = new FilmTextDao(sessionFactory);
        inventoryDao = new InventoryDao(sessionFactory);
        languageDao = new LanguageDao(sessionFactory);
        paymentDao = new PaymentDao(sessionFactory);
        rentalDao = new RentalDao(sessionFactory);
        staffDao = new StaffDao(sessionFactory);
        storeDao = new StoreDao(sessionFactory);
    }

    public static void main(String[] args) {
        Main main = new Main();
        Customer customer = main.createCustomer();
        //main.customerReturnInventoryToStore();
        main.customerRentInventory(customer);
        main.newFilmWasMade();
    }

    private void newFilmWasMade() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Language language = languageDao.getItems(0, 20)
                    .stream()
                    .unordered()
                    .findAny()
                    .get();
            List<Category> categories = categoryDao.getItems(0, 5);
            List<Actor> actors = actorDao.getItems(0, 20);
            Film film = new Film();
            film.setActors(new HashSet<>(actors));
            film.setCategories(new HashSet<>(categories));
            film.setSpecialFeatures(Set.of(Feature.BEHIND_THE_SCENES, Feature.DELETED_SCENES));
            film.setRating(Rating.NC17);
            film.setLength((short)22);
            film.setReplacementCost(BigDecimal.TEN);
            film.setRentalRate(BigDecimal.ZERO);
            film.setLanguage(language);
            film.setDescription("Rust and Go will beat Java and JS and everyone will be jobless. Except for Vlad");
            film.setTitle("Horror movie: Forever Legacy");
            film.setRentalDuration((byte) 44);
            film.setOriginalLanguage(language);
            film.setReleaseYear(Year.now());
            filmDao.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilmId(film.getFilmId());
            filmText.setFilm(film);
            filmText.setDescription("Rust and Go will beat Java and JS and everyone will be jobless. Except for Vlad");
            filmText.setTitle("Horror movie: Forever Legacy");
            filmTextDao.save(filmText);

            session.getTransaction().commit();
        }
    }

    private void customerRentInventory(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Film firstAvaliableFilmForRent = filmDao.getFirstAvailableFilmForRent();
            Inventory inventory = new Inventory();
            Store store = storeDao.getItems(0, 1).get(0);
            inventory.setFilm(firstAvaliableFilmForRent);
            inventory.setStore(store);
            inventoryDao.save(inventory);
            Staff staff = store.getManagerStaffId();
            Rental rental = new Rental();
            rental.setInventory(inventory);
            rental.setCustomer(customer);
            rental.setStaff(staff);
            rental.setRentalDate(LocalDateTime.now());
            rentalDao.save(rental);
            Payment payment = new Payment();
            payment.setRental(rental);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(12.2));
            payment.setStaff(staff);
            paymentDao.save(payment);

            session.getTransaction().commit();
        }
    }

    private void customerReturnInventoryToStore() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Rental anyNonReturnedRental = rentalDao.getAnyNonReturnedRental();
            anyNonReturnedRental.setReturnDate(LocalDateTime.now());
            rentalDao.save(anyNonReturnedRental);
            session.getTransaction().commit();
        }
    }

    private Customer createCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Store store = storeDao.getItems(0, 1).get(0);
            City city = cityDao.getByName("Balikesir");
            Address address = new Address();
            address.setAddress("I am stupid and my code does not work, 666, ");
            address.setCity(city);
            address.setPhone("444-444-444");
            address.setDistrict("Exception");
            addressDao.save(address);
            Customer customer = new Customer();
            customer.setFirstName("Dude");
            customer.setLastName("Error");
            customer.setAddress(address);
            customer.setStore(store);
            customer.setActive(true);
            customer.setEmail("error-dude@gmail.com");
            customerDao.save(customer);
            session.getTransaction().commit();
            return customer;

        }
    }
}
