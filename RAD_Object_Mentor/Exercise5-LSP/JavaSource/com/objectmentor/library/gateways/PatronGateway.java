package com.objectmentor.library.gateways;

import java.util.List;
import java.util.Map;

import com.objectmentor.library.models.Patron;

public interface PatronGateway {

	List<Patron> getPatronList();

	Map<String, Patron> getPatronMap();

	int getNextId();

}
