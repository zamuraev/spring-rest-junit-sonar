package com.zamuraev.controller;

import com.zamuraev.model.Interest;
import com.zamuraev.model.UserAccount;
import com.zamuraev.repository.InterestRepository;
import com.zamuraev.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountRepository userRepo;
    private final InterestRepository interestRepo;

    @GetMapping("/test")
    public String test() {
        return "Hello, world!";
    }

    @PostMapping("/users/register-user")
    public UserAccount registerUser(@RequestBody UserAccount userAccount) {

        return userRepo.save(userAccount);
    }

    @GetMapping("/users/get/all")
    public List<UserAccount> getUsers() {
        return userRepo.findAll();
    }

    @PostMapping("/users/interests/update")
    public Interest updateInterest(@RequestBody Interest interest) {
        Optional<UserAccount> userAccountOpt = userRepo.findById(interest.getUserAccountId());
        UserAccount userAccount = null;
        if (userAccountOpt.isPresent()) {
            userAccount = userAccountOpt.get();
            interest.setUserAccount(userAccount);
        }
        return interestRepo.save(interest);
    }

    @DeleteMapping("users/delete/{interestId}")
    public void deleteInterest(@PathVariable("interestId") int id) {
        interestRepo.deleteById(id);
    }

    @GetMapping("users/matches/{id}")
    public List<UserAccount> findMatches(@PathVariable("id") int id) {
        UserAccount userAccount = null;
        Optional<UserAccount> userAccountOpt = userRepo.findById(id);

        if (userAccountOpt.isPresent()) {
            userAccount = userAccountOpt.get();
        }

        return userRepo.findMatches(
                userAccount.getAge(),
                userAccount.getCity(),
                userAccount.getCountry(),
                userAccount.getId());
    }





}
