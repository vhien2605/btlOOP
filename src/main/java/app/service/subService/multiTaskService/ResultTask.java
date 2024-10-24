package app.service.subService.multiTaskService;


/**
 * Wrapper class for encapsulation result from query database
 * <p>
 * Because the return from repository has many kinds of data types
 * </p>
 *
 * @param <T> {@code T} type of result return from Repository method
 */
public class ResultTask<T> {
    private T data;
    private String nameTypeData;

    public ResultTask(String nameTypeData, T data) {
        this.nameTypeData = nameTypeData;
        this.data = data;
    }

    public String getNameTypeData() {
        return nameTypeData;
    }

    public T getData() {
        return data;
    }
}
