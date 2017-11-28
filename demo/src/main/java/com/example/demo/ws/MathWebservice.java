package com.example.demo.ws;

import javax.jws.WebService;

import org.example.math.Add;
import org.example.math.Answer;
import org.example.math.WsMath;
import org.springframework.beans.factory.annotation.Autowired;

@WebService(targetNamespace = "http://example.org/math", serviceName = "WsMath", endpointInterface = "org.example.math.WsMath")
public class MathWebservice implements WsMath {
	@Autowired
	MathDelegate delegate;

	@Override
	public Answer sum(Add add) {
		return delegate.sum(add);
	}
}
