package com.objectmentor.library.gateways;

import java.util.List;
import java.util.Map;

public interface PatronGateway {

	List getPatronList();

	Map getPatronMap();

	int getNextId();

}
