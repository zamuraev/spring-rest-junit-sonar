package com.zamuraev;

import com.zamuraev.controller.UserAccountController;
import com.zamuraev.model.Interest;
import com.zamuraev.model.UserAccount;
import com.zamuraev.repository.InterestRepository;
import com.zamuraev.repository.UserAccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringRestJunitSonarApplicationTests {

    @Mock
    UserAccountRepository userAccountRepository;

    @Mock
    InterestRepository interestRepository;

    @InjectMocks
    UserAccountController controller;

    @Test
    @DisplayName("RegisterUser_Successful")
    void testRegisterUser() {
        UserAccount userAccount = new UserAccount();
        UserAccount savedUserAccount = new UserAccount();
        savedUserAccount.setId(123);
        Mockito.when(userAccountRepository.save(userAccount)).thenReturn(savedUserAccount);
        UserAccount outputUserAccount = controller.registerUser(userAccount);
        assertNotNull(outputUserAccount);
        assertEquals(123, outputUserAccount.getId());
        Mockito.verify(userAccountRepository).save(userAccount);
    }

    @Test
    @DisplayName("GetAllUsers_Successful")
    void testGetUsers() {
        ArrayList<UserAccount> users = new ArrayList<>();
        users.add(new UserAccount());
        users.add(new UserAccount());
        Mockito.when(userAccountRepository.findAll()).thenReturn(users);
        List<UserAccount> outputUsers = controller.getUsers();
        assertNotNull(outputUsers);
        assertEquals(2, outputUsers.size());
        Mockito.verify(userAccountRepository).findAll();
    }

    @Test
    @DisplayName("UpdateInterest_Successful")
    void testUpdateInterest() {
        Interest interest = new Interest();
        interest.setUserAccountId(123);
        Mockito.when(userAccountRepository.findById(123)).thenReturn(Optional.of(new UserAccount()));
        Interest savedInterest = new Interest();
        savedInterest.setId(123);
        Mockito.when(interestRepository.save(interest)).thenReturn(savedInterest);

        Interest outputInterest = controller.updateInterest(interest);
        assertNotNull(outputInterest);
        assertEquals(123, outputInterest.getId());
        Mockito.verify(userAccountRepository).findById(123);
        Mockito.verify(interestRepository).save(interest);
    }

    @Test
    @DisplayName("DeleteInterest_Successful")
    void testDeleteInterest() {
        Mockito.doNothing().when(interestRepository).deleteById(123);
        controller.deleteInterest(123);
        Mockito.verify(interestRepository).deleteById(123);
    }

    @Test
    @DisplayName("FindMatches_Successful")
    void testFindMatches() {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(123);
        userAccount.setAge(20);
        userAccount.setCity("Austin");
        userAccount.setCountry("USA");

        ArrayList<UserAccount> userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount());
        userAccounts.add(new UserAccount());

        Mockito.when(userAccountRepository.findById(123)).thenReturn(Optional.of(userAccount));
        Mockito.when(userAccountRepository.findMatches(20, "Austin", "USA", 123)).thenReturn(userAccounts);
        List<UserAccount> outputMatches = controller.findMatches(123);

        Mockito.verify(userAccountRepository).findById(123);
        Mockito.verify(userAccountRepository).findMatches(20, "Austin", "USA", 123);

        assertNotNull(outputMatches);
        assertEquals(2, outputMatches.size());
    }

}
