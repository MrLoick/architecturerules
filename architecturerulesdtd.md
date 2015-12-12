# Introduction #

Defines your configuration.


# architecture-rules.dtd versions 2.0-rc1 #

```
<!DOCTYPE architecture [

        <!ELEMENT configuration (sources|cyclicalDependency)*>

        <!ELEMENT architecture (configuration|rules)*>

        <!ELEMENT sources (source)*>
        <!ATTLIST sources no-packages CDATA #REQUIRED>

        <!ELEMENT source (#PCDATA)>
        <!ATTLIST source not-found CDATA #REQUIRED>

        <!ELEMENT cyclicalDependency (#PCDATA)>
        <!ATTLIST cyclicalDependency test CDATA #REQUIRED>

        <!ELEMENT rules (rule)*>
        <!ELEMENT rule (comment|packages|violations)*>
        <!ATTLIST rule id CDATA #REQUIRED>

        <!ELEMENT violations (violation)*>       
        <!ELEMENT violation (#PCDATA)>        
        <!ELEMENT comment (#PCDATA)>
        <!ELEMENT packages (package)*>
        <!ELEMENT package (#PCDATA)>                        
        ]>
```

# architecture-rules.dtd versions 1 #

```

<!DOCTYPE architecture [
        <!ELEMENT architecture (configuration|rules)*>

        <!ELEMENT sources (source)*>
        <!ATTLIST sources no-packages (ignore|exception) "exception">

        <!ELEMENT rule (id|package|violations|comment)*>
        <!ELEMENT violations (violation)*>
        <!ELEMENT rules (rule)*>
        <!ELEMENT violation (#PCDATA)>
        <!ELEMENT configuration (sources|cyclicalDependency)*>
        <!ELEMENT comment (#PCDATA)>

        <!ELEMENT source (#PCDATA)>
        <!ATTLIST sources not-found (ignore|exception) "ignore">

        <!ELEMENT package (#PCDATA)>
        <!ELEMENT id (#PCDATA)>
        <!ELEMENT cyclicalDependency (#PCDATA)>
        <!ATTLIST cyclicalDependency test CDATA #REQUIRED>
        
        ]>

```