package com.info.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.info.entity.Hotel;
import com.info.entity.Rating;
import com.info.entity.User;
import com.info.exception.ResourceNotFoundException;
import com.info.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserRepository repo;
	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);

		return repo.save(user);
	}

	@Override
	public List<User> getAllUsers() {

		return repo.findAll();
	}

	@Override
	public User getUser(String userId) {
		//get user from database with the help  of user repository
        User user = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        // fetch rating of the above user from RATING SERVICE
		// http://localhost:8083/ratings/users/47e38dac-c7d0-4c40-8582-11d15f185fad

		Rating[] ratingsOfUser = restTemplate.getForObject(
				"http://localhost:7002/ratings/users/8ef5bc58-9029-4156-b911-f0514b2043e2", Rating[].class);
		logger.info("{} ", ratingsOfUser);
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		List<Rating> ratingList = ratings.stream().map(rating -> {
			// api call to hotel service to get the hotel
			// http://localhost:8082/hotels/1cbaf36d-0b28-4173-b5ea-f1cb0bc0a791
			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(
					"http://localhost:7001/hotles/getHotelById/68432ed0-f677-4335-88d8-2d0221fd20c2", Hotel.class);
			Hotel hotel = forEntity.getBody();

			logger.info("response status code: {} ", forEntity.getStatusCode());
			// set the hotel to rating
			rating.setHotel(hotel);
			// return the rating
			
		
			return rating;
		}).collect(Collectors.toList());

		user.setRating(ratingList);

		return user;

		return user;
	}

}
