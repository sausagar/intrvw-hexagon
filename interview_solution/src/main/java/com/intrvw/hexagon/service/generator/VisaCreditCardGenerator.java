package com.intrvw.hexagon.service.generator;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intrvw.hexagon.conf.CCSuffix;
import com.intrvw.hexagon.exceptions.TechnicalException;
import com.intrvw.hexagon.model.CreditCardDetailsImpl;
import com.intrvw.hexagon.service.CardTypes;

@Component
public class VisaCreditCardGenerator implements CreditCardGenerator {

	private CCSuffix ccSuffix;

	private ExecutorService executor = Executors.newFixedThreadPool(4);
	private Lock lock = new ReentrantLock();

	@Autowired
	public VisaCreditCardGenerator(CCSuffix ccSuffix) {
		super();
		this.ccSuffix = ccSuffix;
	}

	@Override
	public String generateCCNumber() {
		return ccSuffix.getVisaSuffix() + ThreadLocalRandom.current().nextInt(100000000, 999999999 + 1);
	}

	@Override
	public List<CreditCardDetailsImpl> filterAndGenerateValidCreditCards(final List<String> ccNumbers)
			throws TechnicalException {

		final List<CreditCardDetailsImpl> creditCardList = new CopyOnWriteArrayList<>();

		for (String ccNumber : ccNumbers) {
			executor.submit(() -> {
				try {
					lock.lock();
					if (ccNumber.startsWith(ccSuffix.getVisaSuffix())) {
						creditCardList.add(new CreditCardDetailsImpl(ccNumber, CardTypes.VISA.getValue(), new Date()));
					}
				} finally {
					lock.unlock();
				}

			});

		}
		return Collections.unmodifiableList(creditCardList);
	}

}
