package br.com.wallet.repository;

import br.com.wallet.domain.Wallet;
import br.com.wallet.domain.WalletItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;


@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class WalletItemRepositoryTest {

    private static final Date DATE = new Date();
    private static final String TYPE = "ENTRADA";
    private static final String DESCRIPTION = "Conta de Luz";
    private static final BigDecimal VALUE = BigDecimal.valueOf(65);

    @Autowired
    WalletItemRepository repository;

    @Autowired
    WalletRepository walletRepository;

    @Test
    public void testSave(){
        Wallet wallet = new Wallet();
        wallet.setName("Carteira um");
        wallet.setValue(BigDecimal.valueOf(500));
        walletRepository.save(wallet);

        WalletItem wItem = new WalletItem(1L, wallet, DATE, TYPE, DESCRIPTION, VALUE);
        WalletItem response = repository.save(wItem);

        assertNotNull(response);
        assertEquals(response.getDescription(), DESCRIPTION);
        assertEquals(response.getType(), TYPE);
        assertEquals(response.getValue(), VALUE);
        assertEquals(response.getWallet().getId(), wallet.getId());
    }
}