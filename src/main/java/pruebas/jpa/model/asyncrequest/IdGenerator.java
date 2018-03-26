package pruebas.jpa.model.asyncrequest;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class IdGenerator implements IdentifierGenerator  {
    private static final Logger logger = LoggerFactory.getLogger(IdGenerator.class);

    private static final int LENGTH = 15;

    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        logger.debug("Generating a random key");

        Connection connection = sessionImplementor.connection();
        try(PreparedStatement stmt = connection.prepareStatement(SQL)) {
            for (int i = 0; i < 10; i++) {
                if (i > 0) {
                    logger.debug("Id generation attempt {}", i);
                }
                String candidateKey = getCandidateKey(LENGTH);
                stmt.setString(1, candidateKey);
                try {
                    stmt.executeUpdate();
                    logger.debug("Generated key {}", candidateKey);
                    return candidateKey;
                }
                catch (SQLException e) {
                    if ("23505".equals(e.getSQLState())) {
                        logger.debug("Duplicate Key {}", candidateKey);
                    }
                    else {
                        throw new HibernateException(e);
                    }
                }
            }

        }
        catch (SQLException e) {
            throw new HibernateException(e);
        }

        throw new RuntimeException("Unable to assign random key. Duplicate keys always found");
    }

    private static String getCandidateKey(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return new String(digits);
    }

    private static final String SQL = "INSERT INTO async_request_k VALUES(?)";
}
