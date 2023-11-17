package org.rampup.dao;

import java.util.List;

import org.rampup.exception.PersonNotFoundException;
import org.rampup.model.Person;
import org.rampup.model.PersonAddress;
import org.rampup.model.PersonPassport;
import org.rampup.service.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/** * */
public class Test {    
	public static void main(String[] args) {        
		System.out.println("Hello World");        
		Resource resource = new ClassPathResource("repository-context.xml");
		System.out.println(resource.exists());
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("repository-context.xml");        
		PersonDao personDao = (PersonDao) applicationContext.getBean("personRepository");        
//		PersonAddressDao personAddressDao = (PersonAddressDao) applicationContext.getBean("personAddressRepository");        
//		PersonPassportDao passportDao = (PersonPassportDao) applicationContext.getBean("personPassportRepository");        
		
//		PersonService personService = (PersonService) applicationContext.getBean("personService");
		
		
		// ------------------ PERSON ---------------------//
//		 Person person = new Person();        
//		 person.setFirstName("Test");        
//		 person.setLastName("Morning");        
//		 person.setEmail("morning@gmail.com");        
//		
//		 personDao.createPerson(person);        
		
//		 personDao.deletePerson(552);        
//
//		 List<Person> persons = personDao.searchForPersons();        
//		 System.out.println(persons);   
		 
//			Person person = personDao.findPerson(602);        
//			System.out.println(person);
		
//		 Person updatedPerson = new Person();        
//		 updatedPerson.setId(302);        
//		 updatedPerson.setFirstName("Updated");        
//		 updatedPerson.setLastName("updateLastName");        
//		 updatedPerson.setEmail("updated@email.com");        
//		        
//		 personDao.updatePerson(updatedPerson);    
		
		// ------------------- PERSON PASSPORT ----------------------- //
		 
//		 PersonPassport personPassport = new PersonPassport();        
//		 personPassport.setPassportNumber("123-456-789-111");        
//		 personPassport.setPerson(person);        
//		 
//		 person.setPersonPassport(personPassport);       
//		        
//		 personDao.createPerson(person);  
		 
//		 List<PersonPassport> personPassports = passportDao.findAllPersonPassport();
//		System.out.println(personPassports);
		 
//		 PersonPassport passport = passportDao.findById(3);
//		 System.out.println(passport);
		 
		 
		// ------------------- PERSON ADDRESS ----------------------- //
//		 PersonAddress personAddress = new PersonAddress();        
//		 personAddress.setStreet("Lontoc");        
//		 personAddress.setCity("Taguig");        
//		 personAddress.setZipCode("1630");       
//		 personAddress.setCountry("PH");        
//		 
//		 personAddressDao.addAddress(personAddress);  
		 
//		 person.getAddresses().add(personAddress);          
//		 personDao.createPerson(person);    
		 
//		 List<PersonAddress> personAddresses = personAddressDao.findAlListPersonAddress();
//		 System.out.println(personAddresses);
//		 
//		 PersonAddress updatedPersonAddress = new PersonAddress();        
//		 updatedPersonAddress.setId(2);
//		 updatedPersonAddress.setStreet("General Trias");        
//		 updatedPersonAddress.setCity("Cavite");        
//		 updatedPersonAddress.setZipCode("1230");       
//		 updatedPersonAddress.setCountry("PH");
//		 
//		 personAddressDao.updatePersonAddress(updatedPersonAddress);
//		 
//		 PersonAddress pAddress = personAddressDao.findById(2);
//		 System.out.println(pAddress);
		 
//		personAddressDao.delete(3);
		
		
		// ------------------- SERVICE ----------------------- //   
//		 Person person2;
//		try {
//			person2 = personService.findPerson(752);
//			System.out.println(person2);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 
	}
}