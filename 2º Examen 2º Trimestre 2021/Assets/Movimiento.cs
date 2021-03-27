using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Movimiento : MonoBehaviour
{
private float movimientoEjeX;
    private float movimientoEjeZ;
    private float movimientoEjeY;
    private static float sensibilidadTraslacion;
    private static float sensibilidadRotacion;

    // Start is called before the first frame update
    void Start()
    {
        sensibilidadRotacion=30;
        sensibilidadTraslacion=10;
    }

    // Update is called once per frame
    void Update()
    {
        movimientoEjeX= Input.GetAxis("Horizontal");
        movimientoEjeZ= Input.GetAxis("Vertical");
        if(movimientoEjeX!=0){
            transform.position += transform.forward * movimientoEjeX * Time.deltaTime*sensibilidadTraslacion;
        }
        if(movimientoEjeZ!=0){
            transform.position += -transform.right * movimientoEjeZ * Time.deltaTime*sensibilidadTraslacion;
        }
    }

}
