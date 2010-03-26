package com.objectmentor.library.gateways;

import java.util.List;
import java.util.Map;

public interface BookGateway extends MediaGateway {

	List findAllISBNs();

	Map findAllISBNsAndTitles();

}
