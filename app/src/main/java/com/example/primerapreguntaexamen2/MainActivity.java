package com.example.primerapreguntaexamen2;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText cajaNumeroCompra,cajaNombreProducto,cajaCantidad,cajaUnidadMedida,cajaBuscarProducto;
    Button btnBuscarProducto,btnInsertar,btnMostrar;
    BDTienda db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initializer EditText
        cajaNumeroCompra=findViewById(R.id.cajaNumeroCompra);
        cajaNombreProducto=findViewById(R.id.cajaNombreProducto);
        cajaCantidad=findViewById(R.id.cajaCantidad);
        cajaUnidadMedida=findViewById(R.id.cajaUnidadMedida);
        cajaBuscarProducto=findViewById(R.id.cajaBuscarProducto);
        //Initializer Button
        btnBuscarProducto=findViewById(R.id.btnBuscarProducto);
        btnInsertar=findViewById(R.id.btnInsertar);
        btnMostrar=findViewById(R.id.btnMostrar);
        db=new BDTienda(this);


        //Onclick Method For btnInsert
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int cajaNumeroCompraTXT=Integer.parseInt(cajaNumeroCompra.getText().toString());
            String cajaNombreProductoTXT=cajaNombreProducto.getText().toString();
            int cajaCantidadTXT=Integer.parseInt(cajaCantidad.getText().toString());
            String cajaUnidadMedidaTXT=cajaUnidadMedida.getText().toString();

            Boolean checkinsertdata=db.insertuserdata(cajaNumeroCompraTXT,cajaNombreProductoTXT,cajaCantidadTXT,cajaUnidadMedidaTXT);
                if(checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "Insertado correctamente", Toast.LENGTH_SHORT).show();
                    cajaNumeroCompra.setText("");
                    cajaNombreProducto.setText("");
                    cajaCantidad.setText("");
                    cajaUnidadMedida.setText("");
                }else {
                    Toast.makeText(MainActivity.this, "No se Inserto Correctamente", Toast.LENGTH_SHORT).show();


                }



            }
        });
        //Onclick Method For btnMostrar
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.getdatas();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No hay Reguistros", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Numero de Compra :"+res.getString(0)+"\n");
                    buffer.append("Nombre del Producto :"+res.getString(1)+"\n");
                    buffer.append("Cantidad :"+res.getString(2)+"\n");
                    buffer.append("Unidad de Medida :"+res.getString(3)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Entrada de Usuarios");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
        //Onclick Method For btnBuscarProducto
        btnBuscarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cajaBuscarProductoTXT=cajaBuscarProducto.getText().toString();
                Cursor res=db.getdata(cajaBuscarProductoTXT);
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No hay Reguistros", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Numero de Compra :"+res.getString(0)+"\n");
                    buffer.append("Nombre del Producto :"+res.getString(1)+"\n");
                    buffer.append("Cantidad :"+res.getString(2)+"\n");
                    buffer.append("Unidad de Medida :"+res.getString(3)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Entrada de Usuarios");
                builder.setMessage(buffer.toString());
                builder.show();



            }
        });






    }



}