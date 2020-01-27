package com.mars.account

import com.mars.account.boundaries.dto.SignUpDto
import com.mars.account.config.BeanProvider
import com.mars.account.core.exception.ExistUserIdException
import com.mars.account.core.repositories.AccountRepository
import com.mars.account.core.usecases.SignUpUsecase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.BDDMockito.given

@SpringBootTest(classes = [SignUpUsecase.class])
@ContextConfiguration(classes = [BeanProvider.class])
@ActiveProfiles("baseMock")
class SignUpUsecaseTest extends Specification {

    @Autowired
    PasswordEncoder passwordEncoder

    @MockBean(name = "accountRepository")
    AccountRepository accountRepository

    @Autowired
    SignUpUsecase signUpUsecase

    def setup() {
        given(passwordEncoder.encode(anyString())).willReturn("temp")
    }

    def "Account가 이미 존재하는 경우"() {
        given:
        def signUpDto = new SignUpDto("junsu.jang","temp1234")

        given(accountRepository.existByUserId(anyString())).willReturn(true)

        when:
        signUpUsecase.execute(signUpDto)

        then:
        def e = thrown(ExistUserIdException.class)
        e.message == "UserId(${signUpDto.userId}) already exists."
    }
}
