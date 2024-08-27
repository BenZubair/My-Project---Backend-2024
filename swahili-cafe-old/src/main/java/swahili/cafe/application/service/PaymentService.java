package swahili.cafe.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import swahili.cafe.application.model.Book;
import swahili.cafe.application.model.BookAuthor;
import swahili.cafe.application.model.Expert;
import swahili.cafe.application.model.Payment;
import swahili.cafe.application.repository.PaymentRepository;


import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final BookService bookService;
//    private final BookAuthorService bookAuthorService;


    public Payment makePayment(double amount, long expertId, long bookId) {
        Payment payment = new Payment();
        Expert expert = (Expert) userService.findUserByUserId(expertId);
        Book book = bookService.findBook(bookId);
//        BookAuthor bookAuthor = bookAuthorService.findBookAuthor(bookId);
        payment.setPaymentAmount(amount);
        payment.setExpert(expert);
        payment.setBook(book);
        payment.setStatus("Pending");
        payment.setPaymentDate(new Date());
        log.info("Payment paid successfully");
        return paymentRepository.save(payment);
    }



    public Payment updatePayment(double paymentAmount, String paymentStatus, long paymentId) {
        Payment updatePayment = findPayment(paymentId);
        updatePayment.setPaymentAmount(paymentAmount);
        updatePayment.setStatus(paymentStatus);
        log.info("Payment updated successfully");
        return paymentRepository.save(updatePayment);
    }




    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }


    public List<Payment> getAllPaymentsByExpertUsername(String username) {
        return paymentRepository.findPaymentsByExpertUsername(username);
    }



    public Long getTotalPayments() {
        return paymentRepository.getTotalPayments();
    }


    public Payment findPayment(long paymentId) {
        Payment payment = paymentRepository.findPaymentByPaymentId(paymentId);
        if (payment == null) {
            throw new RuntimeException("Payment not found");
        } else {
            return payment;
        }
    }


    public void deletePayment(long paymentId) {
        Payment payment = findPayment(paymentId);
        paymentRepository.deleteById(payment.getPaymentId());
    }

}
