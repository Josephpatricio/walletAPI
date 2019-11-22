package br.com.wallet.repository;

import br.com.wallet.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testSave(){
        User user = new User();
        user.setName("Teste");
        user.setPassword("1234");
        user.setEmail("teste@teste.com");

        User response = userRepository.save(user);
        assertNotNull(response);
    }
}