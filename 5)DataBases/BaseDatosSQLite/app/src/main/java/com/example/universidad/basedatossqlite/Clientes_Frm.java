package com.example.universidad.basedatossqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Clientes_Frm extends Activity implements View.OnClickListener {

    EditText nombreTxt;
    EditText telefonoTxt;
    EditText IDTxt;
    Button VuelveBtn;
    ListView ListaClientes;

    SQLite_Class controlDB = new SQLite_Class(this);
    Util_Strings_Class UtileStrings = new Util_Strings_Class();
    Util_UI UtilesUI = new Util_UI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes__frm);
        incializaPantalla();
        ListaClientes.setAdapter(UtilesUI.CargaArrayAdapter(this, controlDB.ConsultaClientes()));
        ListViewClick();
    }//Fin onCreate  =======================

    private void ListViewClick(){
        ListaClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int laPosicion = position+1;
                String elementoSeleccionado = (String) ListaClientes.getItemAtPosition(position);
                String[] TextoPartido = UtileStrings.CortaTextos(elementoSeleccionado,",");
                IDTxt.setText(String.valueOf(laPosicion));
                nombreTxt.setText(TextoPartido[1]);
                telefonoTxt.setText(TextoPartido[0]);
            }
        });//End setOnItemClickListener
    }//Fin ListViewClick

    private void incializaPantalla() {
        IDTxt = (EditText) findViewById(R.id.NumClie_Txt);
        nombreTxt = (EditText) findViewById(R.id.NomClie_Txt);
        telefonoTxt = (EditText) findViewById(R.id.Tel_Txt);
        ListaClientes = (ListView) findViewById(R.id.Clientes_listView);
        VuelveBtn = (Button) findViewById(R.id.BackClie_Btn);
        VuelveBtn.setOnClickListener(this);
    }//Fin incializaPantalla =======================

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.BackClie_Btn:
                startActivity(new Intent(Clientes_Frm.this, Principal_Frm.class));
                break;
        }//End switch
    }//Fin onClick =======================

}
