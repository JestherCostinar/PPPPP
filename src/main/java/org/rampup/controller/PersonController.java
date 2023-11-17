package org.rampup.controller;

import java.util.List;

import org.rampup.exception.PersonNotFoundException;
import org.rampup.model.Person;
import org.rampup.service.PersonService;
import org.rampup.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.persistence.Id;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<ApiResponse<List<Person>>> getAllPersons() {
		try {
            List<Person> persons = personService.searchForPersons();
            ApiResponse<List<Person>> response = new ApiResponse<>(true, persons);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<List<Person>> response = new ApiResponse<>(false, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ApiResponse<Person>> getPersonById(@PathVariable long id) {
		try {
            Person person = personService.findPerson(id);

            if (person != null) {
                ApiResponse<Person> response = new ApiResponse<>(true, person);
                return ResponseEntity.ok(response);
            } else {
                ApiResponse<Person> response = new ApiResponse<>(false, null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Person> response = new ApiResponse<>(false, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<Long>> createPerson(@RequestBody Person person) {
        try {
            long createdPersonId = personService.createPerson(person);
            ApiResponse<Long> response = new ApiResponse<>(true, createdPersonId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Long> response = new ApiResponse<>(false, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ApiResponse<Void>> updatePerson(@PathVariable long id, @RequestBody Person updatedPerson) {
        try {
            personService.updatePerson(id, updatedPerson);
            ApiResponse<Void> response = new ApiResponse<>(true, null);
            return ResponseEntity.ok(response);
        } catch (PersonNotFoundException e) {
            ApiResponse<Void> response = new ApiResponse<>(false, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Void> response = new ApiResponse<>(false, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE )
    public ResponseEntity<ApiResponse<Void>> deletePerson(@PathVariable long id) {
        try {
            personService.deletePerson(id);
            ApiResponse<Void> response = new ApiResponse<>(true, null);
            return ResponseEntity.ok(response);
        } catch (PersonNotFoundException e) {
            ApiResponse<Void> response = new ApiResponse<>(false, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<Void> response = new ApiResponse<>(false, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
