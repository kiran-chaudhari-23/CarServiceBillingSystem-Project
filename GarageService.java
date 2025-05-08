
import java.util.List;
import java.util.*;
import java.util.Scanner;

public class GarageService {
    private Map<String, Customer>customersMap;
    private List<Service> availableService;

    public GarageService() {
        this.customersMap = new HashMap<>();
        this.availableService =new ArrayList<>();
        loadServices();
    }
    public void loadServices(){
        availableService.add(new Service("Car Wash",500));
        availableService.add(new Service("Oil Change",700));
        availableService.add(new Service("Wheel Alignment",300));
        availableService.add(new Service("Tyre Replacement",3000));
        availableService.add(new Service("Puncher",400));

    }
    public void addCustomer(String name, String phone, String carNumber, String model){

        Car car=new Car(carNumber,model);
        Customer customer=new Customer(name,phone,car);
        customersMap.put(carNumber,customer);
        System.out.println("Customer added Successfully..");
    }
    public void createInvoice(String carNumber){
        if (!customersMap.containsKey(carNumber))
        {
            System.out.println("NO customer Found with car Number : "+carNumber);
            return;
        }
        System.out.println("Available Services :");

        Scanner sc=new Scanner(System.in);

        Customer customer= customersMap.get(carNumber);
        Invoice invoice=new Invoice(customer);

        System.out.println("Available Services:");
        for(int i=0; i<availableService.size(); i++)
        {
            System.out.println((i+1)+". "+availableService.get(i).getName() + " - ₹"+availableService.get(i).getPrice());
        }

        while (true) {
            System.out.println("Enter Service to add 0 to finish :");
            int choice = sc.nextInt();
            if (choice == 0) break;
            if (choice > 0 && choice <= availableService.size()) {
                invoice.addService(availableService.get(choice-1));
                System.out.println("Service Done.");

            } else {
                System.out.println("Invalid Choice.");
            }
        }
        invoice.printInvoice();

        }
    }

