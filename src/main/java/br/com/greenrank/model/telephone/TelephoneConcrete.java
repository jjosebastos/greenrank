package br.com.greenrank.model.telephone;

public class TelephoneConcrete extends TelephoneFactory{

    @Override
    public Telephone createTelephone() {
        return new Telephone();
    }
}
