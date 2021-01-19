import React, {useEffect, useState} from "react";
import Product from "./product";
import Pagination from '@material-ui/lab/Pagination';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
    root: {
      '& > *': {
        marginTop: theme.spacing(2),
        color: theme.color,
        
      },
    ' & >nav > ul > li > button:focus': {
        outline: 'none',                                                                   
      }    
    },
  }));

function TablePaging (props) {

    const classes = useStyles();

    return (
        <div className={classes.root}>
            {/* <Pagination count={props.totalPages} 
            page={props.active} 
            onChange={props.pageOnChangeEvent}
            variant="outlined" 
            color="secondary"               
            /> */}
        </div>
    )
}
export default TablePaging; 