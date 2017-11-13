package edu.shu.bowling.service

import edu.shu.bowling.common.AccountAlreadyExistException
import edu.shu.bowling.model.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class AccountServiceImplTest extends Specification {
    @Autowired
    private AccountService accountService

    def "Register Account Service"() {

        given: "an account"
        def account = new Account()
        account.setUserId("jignesh1")
        account.setFirstName("Jignesh")
        account.setLastName("Togadiya")
        account.setBirthDate(new Date())
        account.setEmail("about1@test.com")
        account.setPhone("2035554444")
        account.setPassword("@df#Asder123")
        when: "account is registered"
        def result = accountService.register(account)
        then: "registered account should be returned"
        result.firstName == "Jignesh"
    }

    def "Duplicate email account"() {

        given: "an account"
        def account2 = new Account()
        account2.setUserId("rob")
        account2.setFirstName("Rob")
        account2.setLastName("Antonucci")
        account2.setBirthDate(new Date())
        account2.setEmail("about1@test.com")
        account2.setPhone("2035554124")
        account2.setPassword("@df#Asder123")
        when: "account is registered"
        def result2 = accountService.register(account2)

        then: "should throw exception"
        final AccountAlreadyExistException exception = thrown()
        final message = exception.message
        message == "Account Already Exist"
    }

    def "Duplicate user account"() {

        given: "an account"
        def account3 = new Account()
        account3.setUserId("jignesh1")
        account3.setFirstName("Jignesh")
        account3.setLastName("Togadiya")
        account3.setBirthDate(new Date())
        account3.setEmail("about1@test.com")
        account3.setPhone("2035554444")
        account3.setPassword("@df#Asder123")
        when: "account is registered"
        def result3 = accountService.register(account3)

        then: "should throw exception"
        final AccountAlreadyExistException exception = thrown()
        final message = exception.message
        message == "Account Already Exist"
    }

}
