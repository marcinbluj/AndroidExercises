package com.sda.bluj.marcin.androidpart2.repository;

import com.sda.bluj.marcin.androidpart2.R;
import com.sda.bluj.marcin.androidpart2.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RENT on 2017-02-18.
 */

public class ProductRepository implements ProductRepositoryInterface {

    private static ProductRepository mInstance = new ProductRepository();

    private final Map<Integer, Product> mProducts = new HashMap<>();

    private ProductRepository() {
        Product product1 = new Product(1, "Alstremeria", 25, R.drawable.alstremeria);
        Product product2 = new Product(2, "Aster chiński", 10, R.drawable.asterchinski);
        Product product3 = new Product(3, "Celozja pierzasta", 30, R.drawable.celozjapierzasta);
        Product product4 = new Product(4, "Abelia", 50, R.drawable.abelia);
        Product product5 = new Product(5, "Bluszczpospolity", 12, R.drawable.bluszczpospolity);
        Product product6 = new Product(6, "Ciemiernik", 41, R.drawable.ciemiernik);
        Product product7 = new Product(7, "Jałowiec płożący", 34, R.drawable.jalowiecplozacy);
        Product product8 = new Product(8, "Lobelia", 49, R.drawable.lobelia);

        mProducts.put(1,product1);
        mProducts.put(2,product2);
        mProducts.put(3,product3);
        mProducts.put(4,product4);
        mProducts.put(5,product5);
        mProducts.put(6,product6);
        mProducts.put(7,product7);
        mProducts.put(8,product8);

        setProductDescriptions();
    }

    private void setProductDescriptions() {
        List<Product> products = getProducts();
        products.get(0).setmDescription(
                "Alstremeria, krasnolica (Alstroemeria L.) – rodzaj roślin jednoliściennych liczący ok. 65 gatunków. Zasięg tego rodzaju obejmuje środkową część Ameryki Południowej – od Chile po Ekwador na zachodzie i do Argentyny i środkowej Brazylii na wschodzie. Wiele gatunków oraz odmian uprawianych jest z powodu oryginalnych i trwałych kwiatów w celach ozdobnych jako rośliny ogrodowe lub na kwiaty cięte.");
        products.get(1).setmDescription(
                "Aster chiński (Callistephus chinensis (L.) Nees L.) – gatunek rośliny należący do rodziny astrowatych. Nazwa polska jest myląca, bowiem w istocie gatunek ten nie jest obecnie zaliczany przez botaników do rodzaju aster (Aster), lecz do innego rodzaju – Callistephus. Nazwa aster chiński, pochodząca od łacińskiej nazwy Aster chinensis, nadanej temu gatunkowi przez Karola Linneusza, tak się jednak utrwaliła, że funkcjonuje do dzisiaj. Wprowadzona została nowa nazwa tego gatunku – gwiazdosz chiński, ale nie zyskała jak dotąd popularności. Roślina pochodzi z Chin i Japonii, w Polsce jest uprawiana jako jednoroczna roślina ozdobna.");
        products.get(2).setmDescription(
                "Celozja srebrzysta, grzebionatka właściwa, celozja grzebieniasta, koguci grzebień (Celosia argentea L.) – gatunek rocznej rośliny z rodziny szarłatowatych. Roślina rozpowszechniona jako chwast w strefie tropikalnej i zwrotnikowej o trudnym do ustalenia regionie pochodzenia (prawdopodobnie Indie). W tropikach szeroko rozpowszechniony chwast.");
        products.get(3).setmDescription(
                "Abelia – rodzaj roślin z rodziny zimoziołowatych (Linnaeaceae). Obejmuje w zależności od ujęcia systematycznego od 3 do 30 gatunków. Występują one we wschodniej Azji oraz w Meksyku. Niektóre gatunki i mieszańce uprawiane są jako krzewy ozdobne. Walorem są liczne, efektowne kwiaty o długich rurkach korony oraz ładny pokrój.");
        products.get(4).setmDescription(
                "Bluszcz pospolity (Hedera helix L.) – gatunek wiecznie zielonego pnącza należący do rodziny araliowatych (Araliaceae). Gatunek typowy dla rodzaju bluszcz (Hedera). Jest jedynym przedstawicielem rodziny araliowatych we florze Polski i jedynym w niej pnączem o liściach zimotrwałych. Występuje w lasach całej Polski. Poza naturalnym zasięgiem obejmującym Europę i Azję Mniejszą jest gatunkiem inwazyjnym. Bluszcz pospolity uprawiany jest jako roślina doniczkowa, okrywowa, parkowa. W uprawie jest niewymagający, a jego walory podnosi wielość odmian uprawnych o różnorodnych kształtach i barwach liści, sposobach wzrostu i wymaganiach. Jest rośliną miododajną, leczniczą i kosmetyczną. W tradycjach wielu narodów od dawna obecny jako roślina symboliczna, zwłaszcza jako symbol wierności i trwałości życia. W starożytności odgrywał ważną rolę w kultach szeregu bóstw egipskich, greckich i rzymskich.");
        products.get(5).setmDescription(
                "Ciemiernik (Helleborus L.) – rodzaj roślin należących do rodziny jaskrowatych (Ranunculaceae Juss.). Liczy około 20 gatunków. W Europie Zachodniej, od Wysp Brytyjskich po Hiszpanię, występują 2 gatunki. Obszarem o największym zróżnicowaniu rodzaju jest Półwysep Bałkański, gdzie rośnie 8 gatunków ciemierników, a spośród nich niektóre sięgają do Kaukazu. W zachodnich Chinach występuje ciemiernik tybetański o izolowanym zasięgu. Wiele gatunków ciemierników, mieszańców między nimi i odmian uprawnych uprawianych jest jako rośliny ozdobne. Wiele gatunków wykorzystywanych jest także od dawna w zielarstwie oraz w homeopatii.");
        products.get(6).setmDescription(
                "Jałowiec płożący (Juniperus horizontalis Moench.) – gatunek roślin należący do rodziny cyprysowatych. Pochodzi z północnej części Ameryki Północnej, gdzie występuje aż po Alaskę i Jukon. Jest powszechnie uprawiany w wielu krajach, również w Polsce, jako roślina ozdobna.");
        products.get(7).setmDescription(
                "Lobelia (Lobelia L.) – rodzaj roślin z rodziny dzwonkowatych. Należy do niego około 370 gatunków występujących głównie na obszarach o klimacie umiarkowanym i subtropikalnym, szczególnie licznie w obydwu Amerykach i w Afryce. Gatunkiem typowym jest Lobelia dortmanna L..");
    }

    public static ProductRepositoryInterface getInstance() {
        return mInstance;
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(mProducts.values());
    }
}
