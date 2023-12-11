package ParameterObjects;

import bll.interfaces.IPeomBLO;
import dal.interfaces.IBookDAO;
import dal.interfaces.IPoemDAO;
import dal.interfaces.IRootDAO;
import dal.interfaces.ITokenDAO;
import dal.interfaces.IVerseDAO;

public interface IDaoPo {

	IBookDAO getBookDAO();

	IPoemDAO getPoemDAO();

	IRootDAO getRootDAO();

	IVerseDAO getVerseDAO();

	ITokenDAO getTokenDAO();

}
