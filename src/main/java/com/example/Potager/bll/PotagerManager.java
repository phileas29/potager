package com.example.Potager.bll;

import org.springframework.stereotype.Service;

import com.example.Potager.bo.Potager;
import com.example.Potager.dal.PotagerDAO;


@Service
public class PotagerManager extends CRUDManager<Potager, PotagerDAO> {

}
